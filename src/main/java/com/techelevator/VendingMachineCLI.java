package com.techelevator;

import com.techelevator.system.VendingMachine;
import com.techelevator.system.VendingMachineItem;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    public VendingMachineCLI() {

    }

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String SECOND_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String SECOND_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String SECOND_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] SECOND_MENU_OPTIONS = {SECOND_MENU_OPTION_FEED_MONEY, SECOND_MENU_OPTION_SELECT_PRODUCT, SECOND_MENU_OPTION_FINISH_TRANSACTION};

    private Menu menu;
    private Menu secondMenu;
    private BigDecimal balance = new BigDecimal("0.00");
    private BigDecimal resetBalance = new BigDecimal("0.00");

    VendingMachine itemDisplay = new VendingMachine();


    Scanner userInput = new Scanner(System.in);

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance;
    }


    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        itemDisplay.runMenu();

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                 for (VendingMachineItem item : itemDisplay.getMachineItems()) {
                     System.out.println(item);
                 }


            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                System.out.println("Please enter money in whole dollar amounts (i.e. one dollar is \"1\"): ");
                String moneyInputAmount = userInput.nextLine();
                balance = balance.add(new BigDecimal(moneyInputAmount));
                itemDisplay.logTransaction(null, 1, " FEED MONEY: $" + moneyInputAmount + " $", balance);

                        try {
                            while (true) {
                                System.out.println("Current Money Provided: $" + balance);
                                String nextMenuChoice = (String) menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);
                                        if (nextMenuChoice.equals(SECOND_MENU_OPTION_FEED_MONEY)) {
                                            System.out.println("Please enter money into the slot ($1, $5, or $10 bills) and enter amount to the cent (i.e. one dollar is \"1.00\"): ");
                                            moneyInputAmount = userInput.nextLine();
                                            balance = balance.add(new BigDecimal(moneyInputAmount));
                                            itemDisplay.logTransaction(null, 1, " FEED MONEY: $" + moneyInputAmount + " $", balance);
                                        } else if (nextMenuChoice.equals(SECOND_MENU_OPTION_SELECT_PRODUCT)) {
                                            for (VendingMachineItem item : itemDisplay.getMachineItems()) {
                                                System.out.println(item);
                                            }
                                            System.out.println("Please enter slot number: ");
                                            String slotPicked = userInput.nextLine();
                                            if (itemDisplay.validateSlotSelection(slotPicked)) {
                                                balance = itemDisplay.addToPurchase(slotPicked, balance);
                                            } else {
                                                System.out.println("Selection was not valid");
                                            }
                                            itemDisplay.logTransaction(slotPicked, 2, null, balance);
                                            System.out.println(itemDisplay.dispenseItem(slotPicked, balance));
                                        } else if (nextMenuChoice.equals(SECOND_MENU_OPTION_FINISH_TRANSACTION)) {
                                            itemDisplay.logTransaction(null, 3, " GIVE CHANGE: $" + balance + " $", resetBalance);
                                            System.out.println(itemDisplay.dispenseChange(balance));
                                            System.out.println("Thanks, breh");
                                        }
                                    }
                                } catch(Exception ex){
                            System.out.println("Please enter a valid amount.");

                        }




                } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Have a nice day, simp");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
