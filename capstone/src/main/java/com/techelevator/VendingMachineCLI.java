package com.techelevator;

import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

    public VendingMachineCLI() {
    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    public void run() {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Umbrella Corp presents 'It's Raining Chips Vending Machine'");
        while (true) {
            System.out.println("Main Menu\n" + "(1) Display Vending Machine Items\n" + "(2) Purchase\n" + "(3) Exit");
            String mainMenuUserInput = scanner.nextLine();

            if (mainMenuUserInput.equalsIgnoreCase("1")) {
                for (Map.Entry<String, Product> entrySet : bank.getPurchaseInventory().getInventoryMap().entrySet()) {
                    System.out.println(entrySet.getValue());
                }
                System.out.println("Press enter to return to Main Menu: ");
                String anyKey = scanner.nextLine();
            }
        }

    }

}
