package com.techelevator.system;

import java.math.BigDecimal;

public class Drink extends VendingMachineItem implements Purchasable{


    public Drink(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Cheers Glug, Glug!";
    }
}
