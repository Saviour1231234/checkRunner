package dao.impl;

import dao.CardDao;
import entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {

    private final List<Card> listOfCards = new ArrayList<>();

    @Override
    public void save(Integer number, Integer discount) {
        Card card = new Card(number, discount);
        listOfCards.add(card);

    }

    @Override
    public Card getByNumber(Integer number) {
        for (Card cards: listOfCards) {
            if (number.equals(cards.getNumber())){
                return cards;
            }
        }
        return null;
    }
}
