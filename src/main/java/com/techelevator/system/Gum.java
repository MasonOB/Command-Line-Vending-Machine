package com.techelevator.system;

public class Gum extends VendingMachineItem implements Dispensable {


    public Gum(String slotIdentifier, String name, String price, String type, int numOfItems) {
        super(slotIdentifier, name, price, type, numOfItems);
    }

    public Gum() {

    }

    @Override
    public String getMessage() {
        return "Chew Chew, Pop!";
    }
}
