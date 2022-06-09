package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product {
    public Candy(String slotLocation, String productName, BigDecimal price) {
        super(slotLocation, productName, price);
    }

    @Override
    public String getCatchPhrase() {
        return "Munch Munch, Yum!";
    }
}
