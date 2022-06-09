package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Product {

    public Chips(String slotLocation, String productName, BigDecimal price) {
        super(slotLocation, productName,price);
    }

    @Override
    public String getCatchPhrase() {
        return "Crunch Crunch, Yum!";
    }
}
