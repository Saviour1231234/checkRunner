import IO.input.CardInput;
import IO.input.ProductInput;
import IO.input.ReceiptInput;
import IO.output.CheckOutput;
import dao.CardDao;
import dao.ProductDao;
import dao.ReceiptDao;
import dao.impl.CardDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.impl.ReceiptDaoImpl;

import java.io.FileNotFoundException;

public class Main {

    private static final ProductDao productDao = new ProductDaoImpl();
    private static final CardDao cardDao = new CardDaoImpl();
    private static final ReceiptDao receiptDao = new ReceiptDaoImpl();

    private static final CheckOutput check = new CheckOutput(cardDao, productDao, receiptDao);


    public static void main(String[] args) throws FileNotFoundException {

        CardInput cardReader = new CardInput();
        cardReader.read(cardDao);
        // создали и сохранили 5 скидочных карт из файла cards

        ProductInput productReader = new ProductInput();
        productReader.read(productDao);
        //создали и сохранили 20 продуктов из файла products; stock: 1 - товар на акции, 0 - не на акции

        ReceiptInput receiptInput = new ReceiptInput();
        receiptInput.read(receiptDao);
        //создали и сохранили содержимое чека (для примера) из файла checkRunner

        check.print(4444);
        //печать чека в файл check, с учетом скидки по предьявленной карте

    }
}
