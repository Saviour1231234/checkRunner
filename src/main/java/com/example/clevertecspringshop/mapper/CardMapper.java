package com.example.clevertecspringshop.mapper;

import com.example.clevertecspringshop.entity.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Card.builder()
                .id(resultSet.getInt("id"))
                .number(resultSet.getInt("number"))
                .discount(resultSet.getInt("discount"))
                .build();
    }
}
