package com.techelevator.system;

import java.math.BigDecimal;

public class Chip extends VendingMachineItem implements Purchasable{

    public Chip(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Crunch!";
    }
}
