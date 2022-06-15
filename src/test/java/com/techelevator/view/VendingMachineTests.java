package com.techelevator.view;

import com.techelevator.system.VendingMachine;
import com.techelevator.system.VendingMachineItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTests {

    private VendingMachine slotSelected;

    @Before
    public void setUpMethod() { slotSelected = new VendingMachine(); }


    @Test
    public void test_that_A5_returns_original_balance() {

        slotSelected.runMenu();

        BigDecimal result = slotSelected.addToPurchase("A5", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"A5\" did not return 10.00", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY), result);
    }

    @Test
    public void test_that_D4_returns_9_75() {

        slotSelected.runMenu();

        BigDecimal result = slotSelected.addToPurchase("D4", BigDecimal.valueOf(10.50).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"D4\" did not return 9.75", BigDecimal.valueOf(9.75).setScale(2, RoundingMode.UNNECESSARY), result);
    }

    @Test
    public void test_that_A4_returns_chip_message() {

        slotSelected.runMenu();

        String result = slotSelected.dispenseItem("A4", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"A4\" did not return \"Crunch Crunch, Crunch!\"", "Crunch Crunch, Crunch!", result);
    }

    @Test
    public void test_that_B4_returns_candy_message() {

        slotSelected.runMenu();

        String result = slotSelected.dispenseItem("B4", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"B4\" did not return \"Munch Munch, Mmm-Good!\"", "Munch Munch, Mmm-Good!", result);
    }

    @Test
    public void test_that_C1_returns_drink_message() {

        slotSelected.runMenu();

        String result = slotSelected.dispenseItem("C1", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"C1\" did not return \"Cheers Glug, Glug!\"", "Cheers Glug, Glug!", result);
    }

    @Test
    public void test_that_D1_returns_gum_message() {

        slotSelected.runMenu();

        String result = slotSelected.dispenseItem("D1", BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("\"D1\" did not return \"Chew Chew, Pop!\"", "Chew Chew, Pop!", result);
    }

    VendingMachine changeBalance = new VendingMachine();

    @Before
    public void setChangeBalance() { this.changeBalance = changeBalance; }

    @Test
    public void test_that_0_00_returns_0_quarters() {

        changeBalance.runMenu();

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("0.00 did not return 0 quarters", "Here is your change: 0 quarters", result);
    }

    @Test
    public void test_that_1_05_returns_4_and_1() {

        changeBalance.runMenu();

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(1.05).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("1.05 did not return 4 quarters, 1 nickel", "Here is your change: 4 quarters, 1 nickel", result);
    }

    @Test
    public void test_that_2_10_returns_8_and_1() {

        changeBalance.runMenu();

        String result = changeBalance.dispenseChange(BigDecimal.valueOf(2.10).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("2.10 did not return 8 quarters, 1 dime", "Here is your change: 8 quarters, 1 dime", result);
    }

    VendingMachine transactionLog = new VendingMachine();

    @Before
    public void setTransactionLog() { this.transactionLog = transactionLog; }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    @Test
    public void test_log_1_returns_correct_string() {

        transactionLog.runMenu();

        String result = transactionLog.logTransaction(null, 1, " Test Message ", BigDecimal.valueOf(10.000).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("Log Type 1 did not return correct string", dateTimeFormatter.format(LocalDateTime.now()) + " Test Message $" + BigDecimal.valueOf(10.000).setScale(2, RoundingMode.UNNECESSARY), result);

    }

    @Test
    public void test_log_2_returns_correct_string() {

        transactionLog.runMenu();

        String result = transactionLog.logTransaction("A1", 2, null, BigDecimal.valueOf(10.000).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("Log Type 2 did not return correct string", dateTimeFormatter.format(LocalDateTime.now()) + " Potato Crisps A1 $3.05 $" + BigDecimal.valueOf(6.95), result);

    }

    @Test
    public void test_log_3_returns_correct_string() {

        transactionLog.runMenu();

        String result = transactionLog.logTransaction(null, 3, " Test Message ", BigDecimal.valueOf(0.000).setScale(2, RoundingMode.UNNECESSARY));

        Assert.assertEquals("Log Type 3 did not return correct string", dateTimeFormatter.format(LocalDateTime.now()) + " Test Message $" + BigDecimal.valueOf(0.000).setScale(2, RoundingMode.UNNECESSARY), result);

    }

}
