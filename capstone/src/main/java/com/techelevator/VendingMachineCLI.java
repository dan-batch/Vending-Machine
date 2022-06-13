package com.techelevator;

import com.techelevator.exceptions.AddMoneyException;
import com.techelevator.exceptions.InvalidSlotLocationException;
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

        System.out.println("Umbrella Corp presents 'It's Raining Chips!' Vending Machine");
        while (true) {
            System.out.println("Main Menu\n" + "(1) Display Vending Machine Items\n" + "(2) Purchase\n" + "(3) Exit");
            String mainMenuUserInput = scanner.nextLine();

            if (mainMenuUserInput.equalsIgnoreCase("1")) {
                displayInventory(bank, "Press enter to return to Main Menu: ", scanner);
            } else if (mainMenuUserInput.equalsIgnoreCase("2")) {
                while (true) {
                    System.out.println("Purchase Menu\n" + "Current Money Provided: $" + bank.getCurrentMoneyProvided() + "\n" + "\n" + "(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
                    String purchaseMenuUserInput = scanner.nextLine();
                    if (purchaseMenuUserInput.equalsIgnoreCase("1")) {
                        System.out.print("How much money would you like to add? $");
                        String moneyToAddString = scanner.nextLine();
                        try {
                            bank.addMoney(new BigDecimal(moneyToAddString));
                        } catch (AddMoneyException nMe) {
                            System.out.println("This machine doesn't stock pennies. You need to add a positive amount of money that is divisible by at least a nickel.");
                        }
                    } else if (purchaseMenuUserInput.equalsIgnoreCase("2")) {
                        String userPurchaseChoice = displayInventory(bank, "Please enter the slot location of the item you'd like to purchase", scanner);
                        try {
                            bank.purchaseAnItem(userPurchaseChoice.toUpperCase());
                            System.out.printf("You bought %s! %s \n \n",
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
                System.out.println("Thank you for using the vending machine!\nRemember: Umbrella Corp knows your social security number :)");
                break;
            } else if (mainMenuUserInput.equalsIgnoreCase("4")) {
                bank.generateSalesReport();
            }

        }

    }

    private String displayInventory(Bank bank, String x, Scanner scanner) {
        for (Map.Entry<String, Product> entrySet : bank.getPurchaseInventory().getInventoryMap().entrySet()) {
            System.out.println(entrySet.getValue());
        }
        System.out.println(x);
        String anyKey = scanner.nextLine();
        return anyKey;
    }
}
