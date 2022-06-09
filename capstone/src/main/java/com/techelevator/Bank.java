package com.techelevator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bank {

    private BigDecimal currentMoneyProvided = BigDecimal.ZERO;
    private BigDecimal changeProvided;


    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");

    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;

    public Bank() {

    }

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public BigDecimal getChangeProvided() {
        return changeProvided;
    }

    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }

    public void addMoney(BigDecimal moneyToAdd) {
        currentMoneyProvided = currentMoneyProvided.add(moneyToAdd);
        try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
            writer.println(LocalDate.now() + " " + LocalDateTime.now() + " " + moneyToAdd + " " + currentMoneyProvided);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void purchaseItem(String slotLocation){
        Inventory purchaseInventory = new Inventory();
        purchaseInventory.getInventoryList
    }
}
