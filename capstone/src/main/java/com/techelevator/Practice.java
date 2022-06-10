package com.techelevator;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        Inventory ourInventory = new Inventory();


//        System.out.println(ourInventory.getInventoryList());
        Bank bank = new Bank();


        bank.addMoney(new BigDecimal("3.10"));

        System.out.println(bank.getPurchaseInventory().getInventoryMap().get("A1").getQuantity());
        bank.purchaseAnItem("A1");
        System.out.println(bank.getPurchaseInventory().getInventoryMap().get("A1").getQuantity());


    }
}
