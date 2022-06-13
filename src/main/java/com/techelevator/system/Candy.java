package com.techelevator.system;

public class Candy extends VendingMachineItem implements Dispensable {


    public Candy(String slotIdentifier, String name, String price, String type, int numOfItems) {
        super(slotIdentifier, name, price, type, numOfItems);
    }

    public Candy() {

    }

    @Override
    public String getMessage() {
        return "Munch Munch, Mmm-Good!";
    }
}
