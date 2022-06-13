package com.techelevator.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    BigDecimal newBalance = new BigDecimal("0.00");

    Chip chipMessage = new Chip();
    Gum gumMessage = new Gum();
    Drink drinkMessage = new Drink();
    Candy candyMessage = new Candy();


    public VendingMachine() {

    }

    private File vendingMachineMenu = new File("C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\vendingmachine.csv");

    public List<VendingMachineItem> machineItems = new ArrayList<>();


    public List<VendingMachineItem> runMenu() {
        try (Scanner menuReader = new Scanner(vendingMachineMenu)) {
            while (menuReader.hasNextLine()) {
                String currentLine = menuReader.nextLine();
                String[] itemDetails = currentLine.split("\\|");
                String slotIdentifier = itemDetails[0];
                String name = itemDetails[1];
                String price = itemDetails[2];
                String type = itemDetails[3];
                machineItems.add(new VendingMachineItem(slotIdentifier, name, price, type, 5));
            }
            return machineItems;

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        return null;
    }

    // getters/setters

    public List<VendingMachineItem> getMachineItems() {
        return machineItems;
    }

    //methods

    public BigDecimal addToPurchase(String slotPicked, BigDecimal balance) {
        //if the user input matches the slotIdentifier,
        // then get the price and add it to current money provided,
        // subtract 1 from numOfItems
        //



        for (int i = 0; i < machineItems.size(); i++) {
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked) && machineItems.get(i).getNumOfItems() > 0) {

                int numberOfItem = machineItems.get(i).getNumOfItems();
                numberOfItem -= 1;
                machineItems.get(i).setNumOfItems(numberOfItem);


                BigDecimal convertedPrice = new BigDecimal(machineItems.get(i).getPrice());

                newBalance = balance.subtract(convertedPrice);

                return newBalance;

            } else {
                System.out.println("SOLD OUT");
            }
        }
        return balance;

    }

    public boolean validateSlotSelection(String slotPicked) {
        for (int i = 0; i < machineItems.size(); i++) {
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {
                return true;
            }
        }
        return false;
    }

    //prints name, cost, money remaining, and returns the message based on the Type
    public String dispenseItem(String slotPicked, BigDecimal newBalance) {

        for (int i = 0; i < machineItems.size(); i++) {
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {
                System.out.println(machineItems.get(i).getName() + " " + machineItems.get(i).getPrice() + " $" + newBalance);
                if (machineItems.get(i).getType().equals("Chip")) {
                    System.out.println(chipMessage.getMessage());
                } else if (machineItems.get(i).getType().equals("Gum")) {
                    System.out.println(gumMessage.getMessage());
                } else if (machineItems.get(i).getType().equals("Candy")) {
                    System.out.println(candyMessage.getMessage());
                } else if (machineItems.get(i).getType().equals("Drink")) {
                    System.out.println(drinkMessage.getMessage());
                }
            }
        }
        return "";
    }

    public String dispenseChange(BigDecimal newBalance) {
        BigDecimal zero = new BigDecimal(0);
        BigDecimal five = new BigDecimal(5);
        BigDecimal ten = new BigDecimal(10);
        BigDecimal fifteen = new BigDecimal(15);
        BigDecimal twenty = new BigDecimal(20);
        BigDecimal twentyFive = new BigDecimal(25);
        BigDecimal numberOfQuarters = newBalance.divide(twentyFive, RoundingMode.DOWN);

        if (newBalance.remainder(twentyFive).equals(zero)) {
            return "Here is your change: " + numberOfQuarters + " quarters";
        } else if (newBalance.remainder(twentyFive).equals(five)) {
            return "Here is your change: " + numberOfQuarters + " quarters, 2 dimes";
        } else if (newBalance.remainder(twentyFive).equals(ten)) {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 dime, 1 nickel";
        } else if (newBalance.remainder(twentyFive).equals(fifteen)) {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 dime";
        } else if (newBalance.remainder(twentyFive).equals(twenty)) {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 nickel";
        }
        this.newBalance = BigDecimal.valueOf(0.00);
        return "";
    }


    public String logTransaction(String slotPicked, int logType, String transactionMessage, BigDecimal balance) {

        String itemName = "";
        String itemPrice = "";

        for (int i = 0; i < machineItems.size(); i++) {
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {
                itemName = machineItems.get(i).getName();
                itemPrice = machineItems.get(i).getPrice();
            }

        File logFile = new File("C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\Log.txt");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {

            //date - time - (the method or snack name) - amount deposited - new balance
            if (logType == 1) {
                writer.write(dateTimeFormatter.format(LocalDateTime.now()) + transactionMessage + " $" + balance +"\n");
            } else if (logType == 2) {
                writer.write((dateTimeFormatter.format(LocalDateTime.now())) + " " + itemName + " " + slotPicked + " $" + itemPrice + " $" + balance + "\n");
            } else if (logType == 3) {
               writer.write((dateTimeFormatter.format(LocalDateTime.now()) + transactionMessage + " $" + balance + "\n"));
            }

        } catch(FileNotFoundException ex) {
            System.out.println("File not found.");
        }

    }
        return "";

}
}








