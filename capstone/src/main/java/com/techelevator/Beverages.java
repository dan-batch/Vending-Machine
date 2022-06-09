package com.techelevator;

import java.math.BigDecimal;

public class Beverages extends Product {
    public Beverages(String slotLocation, String productName, BigDecimal price) {
        super(slotLocation, productName, price);
    }

    @Override
    public String getCatchPhrase() {
        return "Glug Glug, Yum!";
    }
}
