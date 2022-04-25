package com.example.clevertecspringshop.repository.dao;

import com.example.clevertecspringshop.entity.Product;

public interface ProductDao {

    Product findById(Integer productId);
}
