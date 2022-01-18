package IO.input;

import IO.Reader;
import dao.ProductDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProductInput implements Reader<ProductDao> {
    @Override
    public void read(ProductDao productDao) {
        File products = new File("src/resources/products");
        try (Scanner scanner = new Scanner(products)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                String name = data[0];
                Double price = Double.parseDouble(data[1]);
                int stock = Integer.parseInt(data[2]);
                productDao.save(name, price, stock);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Product file not found");
        }
    }
}
