package com.example.clevertecspringshop.repository.dao;

import com.example.clevertecspringshop.entity.Receipt;

import java.util.List;

public interface ReceiptDao {

    List<Receipt> findAll();
}
