package com.example.clevertecspringshop.repository;

import com.example.clevertecspringshop.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query("from Card c where c.number = :number")
    Card findByNumber(Integer number);

}
