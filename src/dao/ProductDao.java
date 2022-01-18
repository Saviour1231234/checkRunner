package dao;

import entity.Product;

public interface ProductDao {

    void save(String name, Double price, int stock);

    Product getById(Integer id);

}
