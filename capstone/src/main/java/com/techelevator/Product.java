package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private int quantity = 5;
    private int unitsSold = 0;

    public Product(String slotLocation, String productName, BigDecimal price) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public abstract String getCatchPhrase();

    public void adjustQuantity() {
        quantity--;
    }

    public void adjustUnitsSold() {
        unitsSold++;
    }

    @Override
    public String toString() {
        if (quantity == 0) {
            return getSlotLocation() + " | " + getProductName() + " | " + getPrice() + " | " + "Sold Out" + "\n";
        } else {
            return getSlotLocation() + " | " + getProductName() + " | " + getPrice() + " | " + getQuantity() + " remaining" + "\n";
        }
    }
}
