package com.techelevator.system;

import java.math.BigDecimal;

public class VendingMachineItem extends VendingMachine implements Dispensable{

    //instance variables
    private String slotIdentifier;
    private String name;
    private String price;
    private String type;
    private int numOfItems;
    private String message;

    public String getMessage() {
        return message;
    }


    public VendingMachineItem(String slotIdentifier, String name, String price, String type, int numOfItems) {
        this.slotIdentifier = slotIdentifier;
        this.name = name;
        this.price = price;
        this.type = type;
        this.numOfItems = numOfItems;

    }

    public VendingMachineItem() {

    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
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

    public String getType() { return type; }


    //methods
    public String toString() {
        return getSlotIdentifier() + "|" + getName() + "|" + getPrice() + "|" + getNumOfItems() + " in stock";
    }



}
