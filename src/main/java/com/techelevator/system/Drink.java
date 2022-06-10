package com.techelevator.system;

public class Drink extends VendingMachineItem implements Dispensable {


    public Drink(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Cheers Glug, Glug!";
    }
}
