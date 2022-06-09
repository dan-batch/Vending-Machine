package com.techelevator;

public class Gum extends Product {
    public Gum(String slotLocation, String productName, String stringPrice) {
        super(slotLocation, productName, stringPrice);
    }

    @Override
    public String getCatchPhrase() {
        return "Chew Chew, Yum!";
    }
}
