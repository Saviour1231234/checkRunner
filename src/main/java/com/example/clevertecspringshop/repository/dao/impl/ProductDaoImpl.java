package com.example.clevertecspringshop.repository.dao.impl;

import com.example.clevertecspringshop.entity.Product;
import com.example.clevertecspringshop.mapper.ProductMapper;
import com.example.clevertecspringshop.repository.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate template;

    @Autowired
    public ProductDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Product findById(Integer productId) {
        return template.queryForObject("select * from product where id = ?", new ProductMapper(), productId);
    }
}
