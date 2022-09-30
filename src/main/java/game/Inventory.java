package game;


public class Inventory {
    private String invID;
    private String invName;
    private int amount;
    /**
     * create new inventory
     *
     * @author Jiayuan Zhu
     * @author Kartik Sharma
     *
     * @param invID id of inventory
     * @param amount amount of inventory
     * @return inventory created
     */
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
     * Set the amount of Inventory
     * @author Jiayuan Zhu
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the amount of Inventory
     * @author Jiayuan Zhu
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the name of Inventory
     * @author Jiayuan Zhu
     */
    public void setInvName(String invName) {
        this.invName = invName;
    }

    /**
     * Get the name of Inventory
     * @author Jiayuan Zhu
     * @return the name
     */
    public String getName() {
        return invName;
    }

    /**
     * Get the id of Inventory
     * @author Jiayuan Zhu
     */
    public String getInvID(){
        return invID;
    }
    /**
     * Get the name and usage of Inventory
     * @author Jiayuan Zhu
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




}