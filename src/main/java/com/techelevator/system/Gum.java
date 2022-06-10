package com.techelevator.system;

public class Gum extends VendingMachineItem implements Dispensable {


    public Gum(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Chew Chew, Pop!";
    }
}
