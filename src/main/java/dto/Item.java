package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String name;
    private BigDecimal price;
    private int inventory;

    public Item(String name, BigDecimal price, int inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return inventory == item.inventory && Objects.equals(name, item.name) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, inventory);
    }
}
