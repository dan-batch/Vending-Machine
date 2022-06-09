package com.techelevator;

public class Beverages extends Product {
    public Beverages(String slotLocation, String productName, String stringPrice) {
        super(slotLocation, productName, stringPrice);
    }

    @Override
    public String getCatchPhrase() {
        return "Glug Glug, Yum!";
    }
}
