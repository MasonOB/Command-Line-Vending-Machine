package com.techelevator;

import com.techelevator.system.VendingMachine;
import com.techelevator.system.VendingMachineItem;
import com.techelevator.view.Menu;

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

    VendingMachine itemDisplay = new VendingMachine();
    VendingMachine transactionLog = new VendingMachine();

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
                System.out.println("Please enter money into the slot ($1, $5, or $10 bills) and enter amount to the cent (i.e. one dollar is \"1.00\": ");
                String moneyInputAmount = userInput.nextLine();


                try {
                    balance = balance.add(new BigDecimal(moneyInputAmount));
                    while (true) {
                        System.out.println("Current Money Provided: $" + moneyInputAmount);
                        System.out.println("");
                        String nextMenuChoice = (String) secondMenu.getChoiceFromOptions(SECOND_MENU_OPTIONS);
                        if (nextMenuChoice.equals(SECOND_MENU_OPTION_FEED_MONEY)) {
                            balance = new BigDecimal(balance + moneyInputAmount);
                        } else if (nextMenuChoice.equals(SECOND_MENU_OPTION_SELECT_PRODUCT)) {
                            System.out.println(itemDisplay);
                            System.out.println("Please enter slot number: ");
                            String slotPicked = userInput.nextLine();
                            balance = itemDisplay.addToPurchase(slotPicked, balance);
                            itemDisplay.dispenseItem(slotPicked);
                        } else if (nextMenuChoice.equals(SECOND_MENU_OPTION_FINISH_TRANSACTION)) {
                            itemDisplay.dispenseChange();
                            System.out.println("Thanks, breh");
                        }
                    }
                } catch (Exception ex) {
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
