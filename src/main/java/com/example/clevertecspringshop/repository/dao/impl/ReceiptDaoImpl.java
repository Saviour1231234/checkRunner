package com.example.clevertecspringshop.repository.dao.impl;

import com.example.clevertecspringshop.entity.Receipt;
import com.example.clevertecspringshop.mapper.ReceiptMapper;
import com.example.clevertecspringshop.repository.dao.ReceiptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

    private final JdbcTemplate template;

    @Autowired
    public ReceiptDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Receipt> findAll() {
        return template.query("select * from Receipt", new ReceiptMapper());
    }
}
