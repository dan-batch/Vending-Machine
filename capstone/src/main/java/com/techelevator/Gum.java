package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum(String slotLocation, String productName, BigDecimal price) {
        super(slotLocation, productName, price);
    }

    @Override
    public String getCatchPhrase() {
        return "Chew Chew, Yum!";
    }
}
