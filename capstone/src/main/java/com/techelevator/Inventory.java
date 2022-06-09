package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private List<Product> inventoryList = new ArrayList<>();

    public Inventory() {
        File inventoryFile = new File("vendingmachine.csv");

        try (Scanner readOnly = new Scanner(inventoryFile);) {
            while (readOnly.hasNextLine()) {
                String lines = readOnly.nextLine();
                String[] linesArray = lines.split("\\|");
                String productName = linesArray[1];
                if (linesArray[3].equalsIgnoreCase("chip")) {
                    Chips chip = new Chips(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                    inventoryList.add(chip);
                } else if (linesArray[3].equalsIgnoreCase("drink")) {
                    Beverages beverage = new Beverages(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                    inventoryList.add(beverage);
                } else if (linesArray[3].equalsIgnoreCase("candy")) {
                    Candy candy = new Candy(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                    inventoryList.add(candy);
                } else if (linesArray[3].equalsIgnoreCase("gum")) {
                    Gum gum = new Gum(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                    inventoryList.add(gum);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}

