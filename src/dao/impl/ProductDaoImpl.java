package dao.impl;

import dao.ProductDao;
import entity.IdGenerator;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final List<Product> listOfProducts = new ArrayList<>();

    @Override
    public void save(String name, Double price, int stock) {
        Product product = new Product(name, price, stock);
        product.setId(IdGenerator.generate_id());
        listOfProducts.add(product);

    }

    @Override
    public Product getById(Integer id) {
        for (Product product : listOfProducts) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }
}
