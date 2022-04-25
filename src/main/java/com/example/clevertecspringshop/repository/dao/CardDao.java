package com.example.clevertecspringshop.repository.dao;

import com.example.clevertecspringshop.entity.Card;

public interface CardDao {

    Card findByNumber(Integer number);
}
