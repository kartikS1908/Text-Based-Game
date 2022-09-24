package game;

import constants.InvType;

public class Inventory {
    private InvType invName;
    private int amount;

    public Inventory(InvType itemName) {
        this.invName = itemName;
        if (invName == InvType.smallHealth) {
            this.amount = 5;
        }
        else if (invName == InvType.bigHealth) {
            this.amount = 10;
        }
        else if (invName == InvType.smallStamina) {
            this.amount = 5;
        }
        else{
            this.amount = 10;
        }
    }

    public int getAmount() {
        return amount;
    }

    public InvType getItemName() {
        return invName;
    }




}
