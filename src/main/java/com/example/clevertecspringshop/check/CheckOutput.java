package com.example.clevertecspringshop.check;

import com.example.clevertecspringshop.entity.Card;
import com.example.clevertecspringshop.entity.Product;
import com.example.clevertecspringshop.entity.Receipt;
import com.example.clevertecspringshop.service.CardService;
import com.example.clevertecspringshop.service.ProductService;
import com.example.clevertecspringshop.service.ReceiptService;
import com.example.clevertecspringshop.service.email.EmailSenderService;
import com.example.clevertecspringshop.service.listeners.EventManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CheckOutput {

        CardService cardService;
    ProductService productService;
    ReceiptService receiptService;
    EventManager eventManager;
    EmailSenderService emailSenderService;

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    public void discount(Integer discount, Double totalSum, PrintWriter pw) {
        if (discount != 0) {
            pw.println("Discount on presented card\t\t" + discount + "%");
            pw.println("Amount including discount\t\t" + BigDecimal.valueOf(totalSum
                    * ((100 - discount)) / 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
    }

    public int stock() {
        int count = 0;
        for (Receipt products : receiptService.findAllReceipts()) {
            Optional<Product> stock = productService.findByProductId(products.getId());
            if(stock.isPresent()) {
                if (stock.get().getStock() == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    public void print(Integer numberCard) {
        File output = new File("src/main/resources/output");
        try (PrintWriter pw = new PrintWriter(output)) {
            Card card = cardService.findByCardNumber(numberCard);
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
            eventManager.subscribe("print", emailSenderService);
        } catch (NullPointerException e) {
            System.out.println("Failed to determine the presented discount card");
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "Check file not found");
        }
    }

    public Double find(PrintWriter pw) {
        try {
            double totalSum = 0;
            for (Receipt receiptList : receiptService.findAllReceipts()) {
                Optional<Product> productInShop = productService.findByProductId(receiptList.getId());

                if (productInShop.isPresent()) {
                    double sum = productInShop.get().getPrice() * receiptList.getQuantity();

                    String field = receiptList.getQuantity() + "\t\t" + productInShop.get().getName() + "\t\t\t\t" +
                            productInShop.get().getPrice() + "\t" + sum;
                    if (stock() > 4 && productInShop.get().getStock() == 1) {
                        pw.println(field);
                        sum = productInShop.get().getPrice() * receiptList.getQuantity() * 0.9;
                        pw.println("\t( \"" + productInShop.get().getName() + "\" promotion -10%)\t"
                                + BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    } else {
                        pw.println(field);
                    }
                    totalSum = totalSum + sum;
                }
            }
            return totalSum;
        } catch (NullPointerException e) {
            throw new NullPointerException("\n" + "There is no product with the selected id");
        }
    }
}
