package game;


public class Inventory {
    private String invID;
    private String invName;
    private int amount;

    public Inventory(String invID, int amount) {

        this.invID = invID;
        if(invID.equals("h1"))
        {this.invName = "Small Healing Potion";}
        else if(invID.equals("h2"))
        {this.invName = "Big Healing Potion";}
        else if(invID.equals("s1"))
        {this.invName = "Small Stamina Booster";}
        else if(invID.equals("s2"))
        {this.invName = "Big Stamina Booster";}
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

    public String getInvID(){
        return invID;
    }
    /**
     * Get the name and usage of Inventory
     *
     * @return the name and usage
     */
    public String getDes() {
        return switch (this.invID) {
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
    public static void useInv(Character c, Inventory inv) {
        if (inv.invID.equals("h1") || inv.invID.equals("h2")) {
            c.HP += inv.amount;
        }
        else {
            c.stamina += inv.amount;
        }
    }


}