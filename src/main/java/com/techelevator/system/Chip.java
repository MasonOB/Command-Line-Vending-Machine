package com.techelevator.system;

public class Chip extends VendingMachineItem implements Dispensable {

    private String message;
    public Chip() {

    }

    public Chip(String slotIdentifier, String name, String price, String type, int numOfItems) {
        super(slotIdentifier, name, price, type, numOfItems);
    }

    public void setMessage() {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Crunch!";
    }
}
