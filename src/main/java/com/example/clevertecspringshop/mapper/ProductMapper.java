package com.example.clevertecspringshop.mapper;

import com.example.clevertecspringshop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .stock(resultSet.getInt("stock"))
                .build();
    }
}
