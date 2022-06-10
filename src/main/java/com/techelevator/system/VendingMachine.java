package com.techelevator.system;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    BigDecimal newBalance = new BigDecimal("0.00");

    Chip chipMessage = new Chip();

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
                machineItems.add(new VendingMachineItem(slotIdentifier, name, price, type));
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
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {

                int numberOfItem = machineItems.get(i).getNumOfItems();
                numberOfItem -= 1;
                machineItems.get(i).setNumOfItems(numberOfItem);


                BigDecimal convertedPrice = new BigDecimal(machineItems.get(i).getPrice());

                newBalance = balance.subtract(convertedPrice);

            }
        }
        return newBalance;

    }

    //prints name, cost, money remaining, and returns the message based on the Type
    public String dispenseItem(String slotPicked) {

        for (int i = 0; i < machineItems.size(); i++) {
            if (machineItems.get(i).getSlotIdentifier().equals(slotPicked)) {

                System.out.println(machineItems.get(i).getName() + " " + machineItems.get(i).getPrice() + " $" + newBalance);
                if (machineItems.get(i).getType().equals("Chip")) {

                    System.out.println(chipMessage.getMessage());

                }
            }
        }


        //put each line into file
        //date - time - (the method or snack name) - amount deposited - new balance
        /*
    String filePath = "C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\Log.txt";
    File vendingMachineLog = new File(filePath);
    public String logTransaction() {
        try(PrintWriter logger = new PrintWriter(new FileOutputStream(vendingMachineLog, true))) {



        } catch(Exception ex) {
            System.out.println("Unable to write to file");
        }
*/
        return chipMessage.getMessage();
    }
}








