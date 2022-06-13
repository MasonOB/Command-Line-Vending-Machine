package com.techelevator.view;

import com.techelevator.system.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTests {

    VendingMachine slotSelected = new VendingMachine();

    @Before
    public void setSlotSelected() { this.slotSelected = slotSelected; }

    @Test
    public void test_that_A5_returns_original_balance() {

        BigDecimal result = slotSelected.addToPurchase("A5", BigDecimal.valueOf(10.00));

        Assert.assertEquals("\"A5\" did not return 10.00", BigDecimal.valueOf(10.00), result);
    }

    @Test
    public void test_that_D4_returns_9_75() {

        BigDecimal result = slotSelected.addToPurchase("D4", BigDecimal.valueOf(10.50));

        Assert.assertEquals("\"D4\" did not return 9.75", BigDecimal.valueOf(9.75), result);
    }

    @Test
    public void test_that_A4_returns_chip_message() {

        String result = slotSelected.dispenseItem("A4", BigDecimal.valueOf(10.00));

        Assert.assertEquals("\"A4\" did not return \"Crunch Crunch, Crunch!\"", "Crunch Crunch, Crunch!", result);
    }

    @Test
    public void test_that_B4_returns_candy_message() {

        String result = slotSelected.dispenseItem("B4", BigDecimal.valueOf(10.00));

        Assert.assertEquals("\"B4\" did not return \"Munch Munch, Mmm-Good!\"", "Munch Munch, Mmm-Good!", result);
    }

    @Test
    public void test_that_C1_returns_drink_message() {

        String result = slotSelected.dispenseItem("C1", BigDecimal.valueOf(10.00));

        Assert.assertEquals("\"C1\" did not return \"Cheers Glug, Glug!\"", "Cheers Glug, Glug!", result);
    }

    @Test
    public void test_that_D1_returns_gum_message() {

        String result = slotSelected.dispenseItem("D1", BigDecimal.valueOf(10.00));

        Assert.assertEquals("\"D1\" did not return \"Chew Chew, Pop!\"", "Chew Chew, Pop!", result);
    }

    VendingMachine changeBalance = new VendingMachine();

    @Before
    public void setChangeBalance() { this.changeBalance = changeBalance; }

    @Test
    public void test_that_0_00_returns_0_quarters() {

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(0.00));

        Assert.assertEquals("0.00 did not return 0 quarters", "Here is your change: 0 quarters", result);
    }

    @Test
    public void test_that_1_05_returns_4_and_1() {

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(1.05));

        Assert.assertEquals("1.05 did not return 4 quarters, 1 nickel", "Here is your change: 4 quarters, 1 nickel", result);
    }

    @Test
    public void test_that_2_10_returns_8_and_1() {

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(2.10));

        Assert.assertEquals("2.10 did not return 8 quarters, 1 dime", "Here is your change: 8 quarters, 1 dime", result);
    }
}
