package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private int quantity = 5;

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

    public String getQuantity() {
        if (quantity > 0) {
            return String.valueOf(quantity);
        } else {
            return "Sold Out";
        }
    }


    public abstract String getCatchPhrase();

    public void adjustQuantity() {
        quantity--;
    }

    @Override
    public String toString() {
        return getSlotLocation() + " | " + getProductName() + " | " + getPrice() + " | " + getQuantity() + " remaining" + "\n";


    }
}
