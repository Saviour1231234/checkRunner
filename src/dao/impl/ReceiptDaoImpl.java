package dao.impl;

import dao.ReceiptDao;
import entity.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao {

    private final List<Receipt> listOfReceipts = new ArrayList<>();

    @Override
    public void buy(Integer id, Integer quantity) {
        Receipt receipt = new Receipt(id, quantity);
        listOfReceipts.add(receipt);
    }

    @Override
    public List<Receipt> getAll() {
        return new ArrayList<>(listOfReceipts);
    }
}
