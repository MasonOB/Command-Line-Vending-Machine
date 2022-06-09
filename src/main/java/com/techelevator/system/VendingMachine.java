package com.techelevator.system;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private BigDecimal balance;

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

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public List<VendingMachineItem> getMachineItems() { return machineItems; }



}

