package com.techelevator.system;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private String slotPicked;

    VendingMachineItem equalsSlotIdentifier = new VendingMachineItem();
    VendingMachineItem subtractItem = new VendingMachineItem();
    VendingMachineItem gettingThePrice = new VendingMachineItem();

    public VendingMachine() {

    }

    private File vendingMachineMenu = new File("C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\vendingmachine.csv");

    private List<VendingMachineItem> machineItems = new ArrayList<>();

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

    public List<VendingMachineItem> getMachineItems() { return machineItems; }

    //methods

    public BigDecimal addToPurchase() {
        //if the user input matches the slotIdentifier,
        // then get the price and add it to current money provided,
        // subtract 1 from numOfItems
        //
        if (machineItems.contains(slotPicked)) {
            // isolate the array that contains "slotPicked" in order to change "price", "numOfItems"
            machineItems.;
            /*int numberOfItem = equalsSlotIdentifier.getNumOfItems();
            numberOfItem -= 1;
            subtractItem.setNumOfItems(numberOfItem);
            BigDecimal convertedPrice = new BigDecimal(equalsSlotIdentifier.getPrice());
            BigDecimal newBalance = new BigDecimal("0.00");
            newBalance = VendingMachineCLI.getBalance() - convertedPrice;*/
        }

    }

    //prints name, cost, money remaining, and returns the message based on the Type
    public String dispenseItem() {

    }



    //put each line into file
    //date - time - (the method or snack name) - amount deposited - new balance
    String filePath = "C:\\Users\\Student\\workspace\\nlr-8-module-1-capstone-orange-team-7\\Log.txt";
    File vendingMachineLog = new File(filePath);
    public String logTransaction() {
        try(PrintWriter logger = new PrintWriter(new FileOutputStream(vendingMachineLog, true))) {



        } catch(Exception ex) {
            System.out.println("Unable to write to file");
        }

    }






}

