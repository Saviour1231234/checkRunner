package IO.output;

import IO.Writer;
import dao.CardDao;
import dao.ProductDao;
import dao.ReceiptDao;
import entity.Card;
import entity.Product;
import entity.Receipt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckOutput implements Writer {

    private final LocalDate date = LocalDate.now();

    private final LocalTime time = LocalTime.now();

    private final CardDao cardDao;

    private final ProductDao productDao;

    private final ReceiptDao receiptDao;


    public CheckOutput(CardDao cardDao, ProductDao productDao, ReceiptDao receiptDao) {
        this.cardDao = cardDao;
        this.productDao = productDao;
        this.receiptDao = receiptDao;
    }

    @Override
    public int stock() {
        int count = 0;
        for (Receipt products : receiptDao.getAll()) {
            Product stock = productDao.getById(products.getId());
            if (stock.getStock() == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void discount(Integer discount, Double totalSum, PrintWriter pw) {
        if (discount != 0) {
            pw.println("Discount on presented card\t\t" + discount + "%");
            pw.println("Amount including discount\t\t" + BigDecimal.valueOf(totalSum
                    * ((100 - discount)) / 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
    }

    @Override
    public void print(Integer numberCard) throws FileNotFoundException {
        File output = new File("src/resources/output");
        try (PrintWriter pw = new PrintWriter(output)) {
            Card card = cardDao.getByNumber(numberCard);
            pw.println("\t\t  $Clevertec company$");
            pw.println("\t\t NYC, Mayumi beach");
            pw.println("\t\t   p. 8800-555-35-35 \n");
            pw.println("\t cashier: №666 \t date: " + date);
            pw.println("\t\t\t\t\t time: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
            pw.println("-----------------------------------------");
            pw.println("Col.\t" + "Name\t\t" + "Price\t" + "Sum");
            Double totalSum = BigDecimal.valueOf(find(pw)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            pw.println("=========================================");
            pw.println("Sum\t\t\t\t\t\t\t\t" + totalSum);
            discount(card.getDiscount(), totalSum, pw);
            pw.println("*****************************************");
            pw.println("\t\t\tThanks for a purchase!");
        } catch (NullPointerException e) {
            System.out.println("Failed to determine the presented discount card (if any)");
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "Check file not found");
        }
    }

    @Override
    public Double find(PrintWriter pw) {
        try {
            double totalSum = 0;
            for (Receipt productInCheck : receiptDao.getAll()) {
                Product productInShop = productDao.getById(productInCheck.getId());
                double sum = productInShop.getPrice() * productInCheck.getQuantity();
                String field = productInCheck.getQuantity() + "\t\t" + productInShop.getName() + "\t\t\t\t" +
                        productInShop.getPrice() + "\t" + sum;
                if (stock() > 4 && productInShop.getStock() == 1) {
                    pw.println(field);
                    sum = productInShop.getPrice() * productInCheck.getQuantity() * 0.9;
                    pw.println("\t( \"" + productInShop.getName() + "\" promotion -10%)\t"
                            + BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue());
                } else {
                    pw.println(field);
                }
                totalSum = totalSum + sum;
            }
            return totalSum;
        } catch (NullPointerException e) {
            throw new NullPointerException("\n" + "There is no product with the selected id");
        }
    }
}
