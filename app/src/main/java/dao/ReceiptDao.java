package dao;

import entity.Receipt;

import java.util.List;

public interface ReceiptDao {

    void buy(Integer id, Integer quantity);

    List<Receipt> getAll();

}
