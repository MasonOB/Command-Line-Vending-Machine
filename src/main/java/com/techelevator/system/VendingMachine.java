package com.techelevator.system;

import com.techelevator.system.VendingMachineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    public VendingMachine() {

    }

    private File vendingMachineMenu = new File("C:\\Users\\Student\\workspace\\mason-bokon-student-code\\nlr-8-module-1-capstone-orange-team-7\\vendingmachine.csv");

    public List<VendingMachineItem> machineItems = new ArrayList<>();

    public void runMenu() {
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
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }


    //getter


    public List<VendingMachineItem> getMachineItems() {
        return machineItems;
    }

    @Override
    public String toString() {
        return machineItems.toString();
    }
}
