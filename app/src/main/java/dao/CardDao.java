package dao;

import entity.Card;

public interface CardDao {

    void save(Integer number, Integer discount);

    Card getByNumber(Integer number);

}
