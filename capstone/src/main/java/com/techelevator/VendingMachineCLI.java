package com.techelevator;

import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.exceptions.NegativeMoneyException;
import com.techelevator.exceptions.NotEnoughMoneyException;
import com.techelevator.exceptions.SoldOutException;

import java.math.BigDecimal;
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
            } else if (mainMenuUserInput.equalsIgnoreCase("2")) {
                while (true) {
                    System.out.println("Purchase Menu\n" + "Current Money Provided: $" + bank.getCurrentMoneyProvided() + "\n" + "\n" + "(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
                    String purchaseMenuUserInput = scanner.nextLine();
                    if (purchaseMenuUserInput.equalsIgnoreCase("1")) {
                        System.out.print("How much money would you like to add? $");
                        String moneyToAddString = scanner.nextLine();
                        try {
                            bank.addMoney(new BigDecimal(moneyToAddString));
                        } catch (NegativeMoneyException nMe) {
                            System.out.println("You need to add a positive amount of money, fool.");
                        }
                    } else if (purchaseMenuUserInput.equalsIgnoreCase("2")) {
                        for (Map.Entry<String, Product> entrySet : bank.getPurchaseInventory().getInventoryMap().entrySet()) {
                            System.out.println(entrySet.getValue());

                        }
                        System.out.println("Please enter the slot location of the item you'd like to purchase");
                        String userPurchaseChoice = scanner.nextLine();
                        try {
                            bank.purchaseAnItem(userPurchaseChoice.toUpperCase());
                            System.out.printf("You bought a(n) %s! %s \n \n",
                                    bank.getPurchaseInventory().getInventoryMap().get(userPurchaseChoice.toUpperCase()).getProductName(),
                                    bank.getPurchaseInventory().getInventoryMap().get(userPurchaseChoice.toUpperCase()).getCatchPhrase());
                        } catch (InvalidSlotLocationException isle) {
                            System.out.println("That slot location does not exist. Please enter a valid slot location.");
                        } catch (SoldOutException soe) {
                            System.out.println("That item is sold out, please try again.");
                        } catch (NotEnoughMoneyException neme) {
                            System.out.println("You don't have enough money for that item. Please feed me more money!");
                        }
                    } else if (purchaseMenuUserInput.equalsIgnoreCase("3")) {
                        bank.giveChange();
                        System.out.printf("You received %d quarters, %d dimes, and %d nickels, totaling $%s in change.\n \n",
                                bank.getNumberOfQuarters(), bank.getNumberOfDimes(), bank.getNumberOfNickels(), bank.getChangeProvided().toString());
                        break;
                    }
                }

            } else if (mainMenuUserInput.equalsIgnoreCase("3")) {
                break;
            }
        }

    }

}
