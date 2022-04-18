package com.example.clevertecspringshop.service;

import com.example.clevertecspringshop.entity.Card;
import com.example.clevertecspringshop.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public record CardService(CardRepository cardRepository) {

    public Card findByNumber(Integer number) {
        return cardRepository.findByNumber(number);
    }
}
