package game;

import constants.InvType;

public class Inventory {
    private String invName;
    private int amount;

    public Inventory(String invName, int amount) {
        this.invName = invName;
        this.amount = amount;
    }

    /**
     * Get the amount of Inventory
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Get tha name of Inventory
     *
     * @return the name
     */
    public String getName() {
        return invName;
    }

    /**
     * Get the name and usage of Inventory
     *
     * @return the name and usage
     */
    public String getDes() {
        return switch (this.invName) {
            case "h1" -> "Small Healing Potion: Add "+ this.amount+ " HP";
            case "h2" -> "Big Healing Potion: Add "+ this.amount+ " HP";
            case "s1" -> "Small Stamina Booster: Add "+ this.amount+ " Stamina";
            case "s2" -> "Big Stamina Booster: Add "+ this.amount+ " Stamina";
            default -> "";
        };
    }

    /**
     * Use Inventory to add Character HP (h1/h2) or Stamina(s1/s2)
     *
     * @param c Current character
     * @param inv The inventory that is used by character
     */
    public void useInv(Character c, Inventory inv) {
        if (inv.invName == "h1" || inv.invName == "h2") {
            c.HP += inv.amount;
        }
        else {
            c.stamina += inv.amount;
        }
    }




}