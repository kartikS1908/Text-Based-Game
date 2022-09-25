package game;

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

    /**
     * Get the amount of HP or Stamina
     *
     * @return the amount of HP or Stamina that should be increased
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Get the name of the Inventory
     *
     * @return the name of Inventory
     */
    public InvType getItemName() {
        return invName;
    }




}
