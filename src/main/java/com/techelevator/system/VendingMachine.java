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
    String returnMessage = "";

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

            } else if (machineItems.get(i).getSlotIdentifier().equals(slotPicked) && machineItems.get(i).getNumOfItems() == 0) {
                System.out.println("SOLD OUT");
                break;
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
                    returnMessage = chipMessage.getMessage();
                    break;
                } else if (machineItems.get(i).getType().equals("Gum")) {
                    returnMessage =  gumMessage.getMessage();
                    break;
                } else if (machineItems.get(i).getType().equals("Candy")) {
                    returnMessage =  candyMessage.getMessage();
                    break;
                } else {
                    returnMessage =  drinkMessage.getMessage();
                    break;
                }
            }
        }
        return returnMessage;
    }

    public String dispenseChange(BigDecimal newBalance) {

        double newDouble = newBalance.doubleValue() * 100;
        int numberOfQuarters = (int)newDouble / 25;


        if (newDouble % 25 == 0) {
            return "Here is your change: " + numberOfQuarters + " quarters";
        } else if (newDouble % 25 == 20) {
            return "Here is your change: " + numberOfQuarters + " quarters, 2 dimes";
        } else if (newDouble % 25 == 15) {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 dime, 1 nickel";
        } else if (newDouble % 25 == 10) {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 dime";
        } else {
            return "Here is your change: " + numberOfQuarters + " quarters, 1 nickel";
        }
    }


        /*
        BigDecimal zero = new BigDecimal(0.00);
        BigDecimal five = new BigDecimal(5);
        BigDecimal ten = new BigDecimal(10);
        BigDecimal fifteen = new BigDecimal(15);
        BigDecimal pointTen = new BigDecimal(0.10);
        BigDecimal pointFifteen = new BigDecimal(0.15);
        BigDecimal pointTwenty = new BigDecimal(0.20);
        BigDecimal twentyFive = new BigDecimal(25);
        BigDecimal pointTwoFive = new BigDecimal(0.25);
        BigDecimal numberOfQuarters = newBalance.divide(pointTwoFive, RoundingMode.DOWN);
        BigDecimal numOfQuartersRounded = numberOfQuarters.setScale(0, RoundingMode.DOWN);

        BigDecimal changeBalance = newBalance.subtract(numOfQuartersRounded.multiply(pointTwoFive));




        if (newBalance.compareTo(BigDecimal.ZERO) == 0) {
            return "Here is your change: " + numberOfQuarters.setScale(0, RoundingMode.DOWN) + " quarters";
        } else if (changeBalance.remainder(pointTwenty).compareTo(BigDecimal.ONE) == 1) {
            return "Here is your change: " + numberOfQuarters.setScale(0, RoundingMode.DOWN) + " quarters, 2 dimes";
        } else if (newBalance.remainder(pointFifteen).compareTo(BigDecimal.ONE) == 1) {
            return "Here is your change: " + numberOfQuarters.setScale(0, RoundingMode.DOWN) + " quarters, 1 dime, 1 nickel";
        } else if (newBalance.remainder(pointTen).compareTo(BigDecimal.ONE) == 1) {
            return "Here is your change: " + numberOfQuarters.setScale(0, RoundingMode.DOWN) + " quarters, 1 dime";
        } else {
            return "Here is your change: " + numberOfQuarters.setScale(0, RoundingMode.DOWN) + " quarters, 1 nickel";
        }
    }
*/

    public String logTransaction(String slotPicked, int logType, String transactionMessage, BigDecimal balance) {

        String itemName = "";
        String itemPrice = "";


        File logFile = new File("C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\Log.txt");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {

            //date - time - (the method or snack name) - amount deposited - new balance
            if (logType == 1) {
                writer.write(dateTimeFormatter.format(LocalDateTime.now()) + transactionMessage + balance + "\n");
            } else if (logType == 2) {
                for (int i = 0; i < machineItems.size(); i++) {
                    if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {
                        itemName = machineItems.get(i).getName();
                        itemPrice = machineItems.get(i).getPrice();
                        break;
                    }
                }

                writer.write((dateTimeFormatter.format(LocalDateTime.now())) + " " + itemName + " " + slotPicked + " $" + itemPrice + " $" + balance + "\n");
            } else if (logType == 3) {
               writer.write((dateTimeFormatter.format(LocalDateTime.now()) + transactionMessage + balance + "\n"));
            }

        } catch(FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        return "";
    }


}








