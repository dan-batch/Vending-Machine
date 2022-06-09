package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private int quantity = 5;
    private String catchPhrase = "";

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

    public String getCatchPhrase() {
        return catchPhrase;
    }

    @Override
    public String toString() {
        return getSlotLocation() + " | " + getProductName() + " | " + getPrice() + "\n";
    }
}
