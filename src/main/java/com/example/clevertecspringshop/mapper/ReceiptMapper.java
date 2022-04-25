package com.example.clevertecspringshop.mapper;

import com.example.clevertecspringshop.entity.Product;
import com.example.clevertecspringshop.entity.Receipt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements RowMapper<Receipt> {
    @Override
    public Receipt mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Receipt.builder()
                .id(resultSet.getInt("id"))
                .quantity(resultSet.getInt("quantity"))
                .build();
    }
}
