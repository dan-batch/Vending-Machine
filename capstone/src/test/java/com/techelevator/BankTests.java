package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankTests {

    private Bank sut;


    @Before
    public void Setup() {
        sut = new Bank();
    }

    @Test
    public void return_all_change() {
        sut.setCurrentMoneyProvided(new BigDecimal("5.15"));
        String actual = sut.giveChange();
        Assert.assertEquals("Should return 5.15", "5.15", actual);
    }

    @Test
    public void zero_cmp_should_return_0_change() {
        sut.setCurrentMoneyProvided(BigDecimal.ZERO);
        String actual = sut.giveChange();
        Assert.assertEquals("Should return 0", "0", actual);
    }

    @Test
    public void add_money_to_cmp() {
        BigDecimal moneyToAdd = new BigDecimal("4.26");
        BigDecimal result = new BigDecimal("4.26");
        sut.addMoney(moneyToAdd);
        BigDecimal actual = sut.getCurrentMoneyProvided();
        Assert.assertEquals("Should be 4.26", result, actual);
    }

    @Test
    public void add_two_deposits_together() {
        BigDecimal firstMoneyToAdd = new BigDecimal("4.00");
        BigDecimal secondMoneyToAdd = new BigDecimal("0.25");
        BigDecimal result = new BigDecimal("4.25");
        sut.addMoney(firstMoneyToAdd);
        sut.addMoney(secondMoneyToAdd);
        BigDecimal actual = sut.getCurrentMoneyProvided();
        Assert.assertEquals("Should be 4.25", result, actual);
    }
    

    @Test
    public void purchase_reduces_current_money() {
        sut.setCurrentMoneyProvided(new BigDecimal("2.00"));
        BigDecimal result = new BigDecimal(".20");
        sut.purchaseAnItem("B1");
        BigDecimal actual = sut.getCurrentMoneyProvided();
        Assert.assertEquals("Should be .20", result, actual);

    }

    @Test
    public void purchase_decrements_quantity() {
        sut.addMoney(new BigDecimal("3.10"));
        sut.purchaseAnItem("A1");
        int actual = sut.getPurchaseInventory().getInventoryMap().get("A1").getQuantity();
        Assert.assertEquals("Should be 4", 4, actual);

    }

}
