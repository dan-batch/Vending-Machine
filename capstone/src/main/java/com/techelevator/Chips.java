package com.techelevator;

public class Chips extends Product {

    public Chips(String slotLocation, String productName, String stringPrice) {
        super(slotLocation, productName, stringPrice);
    }

    @Override
    public String getCatchPhrase() {
        return "Crunch Crunch, Yum!";
    }
}
