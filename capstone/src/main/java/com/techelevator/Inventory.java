package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {
    private Map<String, Product> inventoryMap = new HashMap<>();

    public Inventory() {
        File inventoryFile = new File("vendingmachine.csv");

        try (Scanner readOnly = new Scanner(inventoryFile);) {
            while (readOnly.hasNextLine()) {
                String lines = readOnly.nextLine();
                String[] linesArray = lines.split("\\|");
                Product product;
                if (linesArray[3].equalsIgnoreCase("chip")) {
                    product = new Chips(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                } else if (linesArray[3].equalsIgnoreCase("drink")) {
                    product = new Beverages(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                } else if (linesArray[3].equalsIgnoreCase("candy")) {
                    product = new Candy(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                } else {
                    product = new Gum(linesArray[0], linesArray[1], new BigDecimal(linesArray[2]));
                }
                inventoryMap.put(product.getSlotLocation(), product);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public Map<String, Product> getInventoryMap() {
        return inventoryMap;


    }

}

