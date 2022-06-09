package com.techelevator.system;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem implements Purchasable{


    public Candy(String slotIdentifier, String name, String price, String type) {
        super(slotIdentifier, name, price, type);
    }

    @Override
    public String getMessage() {
        return "Munch Munch, Mmm-Good!";
    }
}
