package com.techelevator;

public class Candy extends Product {
    public Candy(String slotLocation, String productName, String stringPrice) {
        super(slotLocation, productName, stringPrice);
    }

    @Override
    public String getCatchPhrase() {
        return "Munch Munch, Yum!";
    }
}
