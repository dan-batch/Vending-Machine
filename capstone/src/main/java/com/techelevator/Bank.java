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

    public void purchaseItem(String slotLocation) {
        Inventory purchaseInventory = new Inventory();
        Product purchaseItem = purchaseInventory.getInventoryMap().get(slotLocation);
        if ((currentMoneyProvided.compareTo(purchasePrice) == 0 || currentMoneyProvided.compareTo(purchasePrice) == 1)
        && purchaseItem.getQuantity() > 0){
            currentMoneyProvided = currentMoneyProvided.subtract(purchasePrice);
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(LocalDate.now() + " " + LocalDateTime.now() + " "
                        + purchaseName + " "
                        + slotLocation + " "
                        + purchasePrice + " " + currentMoneyProvided);
            } catch (IOException e) {
                System.out.println("File not found");
            }
//in UI: sout(purchaseInventory.
        }
    }

    public String giveChange(){for (int i = 0; (currentMoneyProvided.compareTo(BigDecimal.ZERO) == 1); i++){
        if (currentMoneyProvided.compareTo(QUARTER) == 1 || currentMoneyProvided.compareTo(QUARTER) == 0){
            numberOfQuarters ++;
            currentMoneyProvided = currentMoneyProvided.subtract(QUARTER);
            changeProvided = changeProvided.add(QUARTER);
        }else if ((currentMoneyProvided.compareTo(DIME) == 1 || currentMoneyProvided.compareTo(DIME) == 0)
                && (currentMoneyProvided.compareTo(QUARTER) == -1)){
            numberOfDimes++;
            currentMoneyProvided = currentMoneyProvided.add(DIME);
            changeProvided = changeProvided.add(DIME);
        }else if((currentMoneyProvided.compareTo(NICKEL) == 1 || currentMoneyProvided.compareTo(NICKEL) == 0)
                && (currentMoneyProvided.compareTo(DIME) == -1)){
            numberOfNickels++;
            currentMoneyProvided = currentMoneyProvided.add(NICKEL);
            changeProvided = changeProvided.add(NICKEL);
        }
    }return changeProvided.toString();

        // in UI: souf("You received %d quarters, %d dimes, and %d nickels, totaling %s." numberOfQuarters, numberOfDimes, numberOfNickels, changeProvided.toString());




    }
}
