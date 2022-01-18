package IO.input;

import IO.Reader;
import dao.ReceiptDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReceiptInput implements Reader<ReceiptDao> {
    @Override
    public void read(ReceiptDao receiptDao) {
        File checkRunner = new File("src/resources/receipts");
        try (Scanner scanner = new Scanner(checkRunner)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                Integer id = Integer.parseInt(data[0]);
                Integer quantity = Integer.parseInt(data[1]);
                receiptDao.buy(id, quantity);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "The file with the goods in the check was not found");
        }
    }
}
