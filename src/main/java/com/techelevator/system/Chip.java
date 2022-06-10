package com.techelevator.system;

public class Chip extends VendingMachineItem implements Dispensable {

    private String message;
    public Chip() {

    }

    public Chip(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    public void setMessage() {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Crunch!";
    }
}
