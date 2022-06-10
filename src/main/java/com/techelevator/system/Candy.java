package com.techelevator.system;

public class Candy extends VendingMachineItem implements Dispensable {


    public Candy(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    public Candy() {

    }

    @Override
    public String getMessage() {
        return "Munch Munch, Mmm-Good!";
    }
}
