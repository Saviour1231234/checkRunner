package IO.input;

import IO.Reader;
import dao.CardDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardInput implements Reader<CardDao> {

    @Override
    public void read(CardDao cardDao) {
        File cards = new File("src/resources/cards");
        try (Scanner scanner = new Scanner(cards)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                Integer number = Integer.parseInt(data[0]);
                Integer discount = Integer.parseInt(data[1]);
                cardDao.save(number, discount);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Discount card file not found");
        }
    }
}
