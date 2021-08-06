package dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal cost;
    private int inventory;

    public Item(String name, BigDecimal cost, int inventory) {
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
