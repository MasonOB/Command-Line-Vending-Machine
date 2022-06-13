package com.techelevator.system;

public class Drink extends VendingMachineItem implements Dispensable {


    public Drink(String slotIdentifier, String name, String price, String type, int numOfItems) {
        super(slotIdentifier, name, price, type, numOfItems);
    }

    public Drink() {

    }

    @Override
    public String getMessage() {
        return "Cheers Glug, Glug!";
    }
}
