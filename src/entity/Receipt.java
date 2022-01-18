package entity;

import java.util.Objects;

public class Receipt {

    private Integer id;

    private Integer quantity;

    public Receipt(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(id, receipt.id) && Objects.equals(quantity, receipt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
