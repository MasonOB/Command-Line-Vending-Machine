package com.techelevator.system;

import java.math.BigDecimal;

public class VendingMachineItem {

    //instance variables
    private String slotIdentifier;
    private String name;
    private String price;
    private String type;
    private int numOfItems = 5;


    public VendingMachineItem(String slotIdentifier, String name, String price, String type) {
        this.slotIdentifier = slotIdentifier;
        this.name = name;
        this.price = price;
        this.type = type;

    }

    //getters & setters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }


    public String toString() {
        return getSlotIdentifier() + "|" + getName() + "|" + getPrice() + "|" + getNumOfItems() + " in stock";
    }
}
