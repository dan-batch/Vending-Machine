package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String slotLocation;
    private String productName;
    private String stringPrice;
    private BigDecimal price;
    private int quantity = 5;
    private String catchPhrase = "";

    public Product(String slotLocation, String productName, String stringPrice) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        BigDecimal price = new BigDecimal(this.stringPrice);
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public String getStringPrice() {
        return stringPrice;
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
}
