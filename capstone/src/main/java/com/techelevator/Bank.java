package com.techelevator;

import com.techelevator.exceptions.NotEnoughMoneyException;
import com.techelevator.exceptions.SoldOutException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bank {

    private BigDecimal currentMoneyProvided = BigDecimal.ZERO;
    private BigDecimal changeProvided = BigDecimal.ZERO;

    private Inventory purchaseInventory = new Inventory();


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

    /*FOR TESTING PURPOSES ONLY*/
    public void setCurrentMoneyProvided(BigDecimal currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
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

    public Inventory getPurchaseInventory(){
        return purchaseInventory;
    }

    public void addMoney(BigDecimal moneyToAdd) {
        if (moneyToAdd.compareTo(BigDecimal.ZERO) == 1) {
            currentMoneyProvided = currentMoneyProvided.add(moneyToAdd);
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(LocalDateTime.now() + " $" + moneyToAdd + " $" + currentMoneyProvided);
            } catch (IOException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("You must add a positive value of money, fool");
        }
    }

    public void purchaseAnItem(String slotLocation) {
        Product purchaseItem = purchaseInventory.getInventoryMap().get(slotLocation);
        if (purchaseItem.getQuantity() == 0) {
            throw new SoldOutException();
        } else if ((currentMoneyProvided.compareTo(purchaseItem.getPrice()) == 0 || currentMoneyProvided.compareTo(purchaseItem.getPrice()) == 1)
                && purchaseItem.getQuantity() > 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(purchaseItem.getPrice());
            purchaseItem.adjustQuantity();
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(LocalDateTime.now() + " "
                        + purchaseItem.getProductName() + " "
                        + slotLocation + " $"
                        + purchaseItem.getPrice() + " $" + currentMoneyProvided);
            } catch (IOException e) {
                System.out.println("File not found");
            }
//in UI: sout(purchaseItem.getCatchPhrase);
        } else if (currentMoneyProvided.compareTo(purchaseItem.getPrice()) == -1) {
            throw new NotEnoughMoneyException();
        }
    }

    public String giveChange() {
        for (int i = 0; (currentMoneyProvided.compareTo(BigDecimal.ZERO) == 1); i++) {
            if (currentMoneyProvided.compareTo(QUARTER) == 1 || currentMoneyProvided.compareTo(QUARTER) == 0) {
                numberOfQuarters++;
                currentMoneyProvided = currentMoneyProvided.subtract(QUARTER);
                changeProvided = changeProvided.add(QUARTER);
            } else if ((currentMoneyProvided.compareTo(DIME) == 1 || currentMoneyProvided.compareTo(DIME) == 0)
                    && (currentMoneyProvided.compareTo(QUARTER) == -1)) {
                numberOfDimes++;
                currentMoneyProvided = currentMoneyProvided.subtract(DIME);
                changeProvided = changeProvided.add(DIME);
            } else if ((currentMoneyProvided.compareTo(NICKEL) == 1 || currentMoneyProvided.compareTo(NICKEL) == 0)
                    && (currentMoneyProvided.compareTo(DIME) == -1)) {
                numberOfNickels++;
                currentMoneyProvided = currentMoneyProvided.subtract(NICKEL);
                changeProvided = changeProvided.add(NICKEL);
            }
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(LocalDateTime.now() + " "
                        + "GIVE CHANGE: $"
                        + changeProvided + " $"
                        + currentMoneyProvided);
            } catch (IOException e) {
                System.out.println("File not found");
            }

        }
        return changeProvided.toString();
        //In UI: System.out.printf("You received %d quarters, %d dimes, and %d nickels, totaling %s.", numberOfQuarters, numberOfDimes, numberOfNickels, changeProvided.toString());
    }
}
