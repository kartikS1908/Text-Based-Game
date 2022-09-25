package game;

public enum InvType {
    // Four types of Inventory
    smallHealth, bigHealth, smallStamina, bigStamina;


    /**
     * Get the description of each Inventory Type (name and usage)
     *
     * @return the name and usage
     */
    public String getDescription() {
        return switch (this) {
            case smallHealth -> "Small Healing Potion: Add 5 HP";
            case bigHealth -> "Big Healing Potion: Add 10 HP";
            case smallStamina -> "Small Stamina Booster: Add 3 Stamina";
            case bigStamina -> "Big Stamina Booster: Add 5 Stamina";
        };
    }

}
