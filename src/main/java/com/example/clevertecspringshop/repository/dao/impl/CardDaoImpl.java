package com.example.clevertecspringshop.repository.dao.impl;

import com.example.clevertecspringshop.entity.Card;
import com.example.clevertecspringshop.mapper.CardMapper;
import com.example.clevertecspringshop.repository.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CardDaoImpl implements CardDao {

    private final JdbcTemplate template;

    @Autowired
    public CardDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Card findByNumber(Integer number) {
        return template.queryForObject("select * from Card c where c.number = ?", new CardMapper(), number);
    }
}
