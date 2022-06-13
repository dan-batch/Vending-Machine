package com.techelevator;

import com.techelevator.exceptions.AddMoneyException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.exceptions.NotEnoughMoneyException;
import com.techelevator.exceptions.SoldOutException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Bank {

    private BigDecimal currentMoneyProvided = BigDecimal.ZERO;
    private BigDecimal changeProvided = BigDecimal.ZERO;

    private Inventory purchaseInventory = new Inventory();

    private BigDecimal totalSales = BigDecimal.ZERO;


    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");

    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");

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

    public Inventory getPurchaseInventory() {
        return purchaseInventory;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void addMoney(BigDecimal moneyToAdd) {
        BigDecimal centsInADollar = new BigDecimal("100");
        BigDecimal nickelTimes100 = new BigDecimal("5");
        if (moneyToAdd.compareTo(BigDecimal.ZERO) == 1
                && ((moneyToAdd.multiply(centsInADollar)).remainder(nickelTimes100)).compareTo(BigDecimal.ZERO) == 0) {
            currentMoneyProvided = currentMoneyProvided.add(moneyToAdd);
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(dtf.format(LocalDateTime.now()) + " FEED MONEY:" + " $" + moneyToAdd + " $" + currentMoneyProvided);
            } catch (IOException e) {
                System.out.println("File not found");
            }
        } else {
            throw new AddMoneyException();
        }
    }

    public void purchaseAnItem(String slotLocation) {
        Product purchaseItem = purchaseInventory.getInventoryMap().get(slotLocation.toUpperCase());
        if (purchaseItem == null) {
            throw new InvalidSlotLocationException();
        }
        if (purchaseItem.getQuantity() == 0) {
            throw new SoldOutException();
        } else if ((currentMoneyProvided.compareTo(purchaseItem.getPrice()) == 0 || currentMoneyProvided.compareTo(purchaseItem.getPrice()) == 1)
                && purchaseItem.getQuantity() > 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(purchaseItem.getPrice());
            purchaseItem.adjustQuantity();
            purchaseItem.adjustUnitsSold();
            totalSales = getTotalSales().add(purchaseItem.getPrice());
            try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
                writer.println(dtf.format(LocalDateTime.now()) + " "
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
        }
        try (FileOutputStream fos = new FileOutputStream("Log.txt", true); PrintWriter writer = new PrintWriter(fos)) {
            writer.println(dtf.format(LocalDateTime.now()) + " "
                    + "GIVE CHANGE: $"
                    + changeProvided + " $"
                    + currentMoneyProvided);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return changeProvided.toString();
    }

    public void generateSalesReport() {
        File salesReport = new File(dtf.format(LocalDateTime.now()) + "_Sales_Report.txt");
        try (FileOutputStream fos = new FileOutputStream(salesReport, true); PrintWriter salesWriter = new PrintWriter(fos)) {
            for (Map.Entry<String, Product> salesReportEntry : purchaseInventory.getInventoryMap().entrySet()) {
                salesWriter.println(salesReportEntry.getValue().getProductName() + "|" + salesReportEntry.getValue().getUnitsSold());
            }
            salesWriter.print("**TOTAL SALES** $" + totalSales);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
