package com.techelevator.system;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem implements Purchasable{


    public Gum(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Chew Chew, Pop!";
    }
}
