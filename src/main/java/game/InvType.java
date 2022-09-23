package game;

public enum InvType {
    smallHealth, bigHealth, smallStamina, bigStamina;


    /**
     * @return the name and usage
     */
    public String getName() {
        return switch (this) {
            case smallHealth -> "Small Healing Potion: Add 5 HP";
            case bigHealth -> "Big Healing Potion: Add 10 HP";
            case smallStamina -> "Small Stamina Booster: Add 3 Stamina";
            case bigStamina -> "Big Stamina Booster: Add 5 Stamina";
        };
    }

}
