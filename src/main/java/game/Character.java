package game;

public class Character {
    int HP, stamina, charID, treasureCurr;
    String name;
    Weapon weapon;
    Room currentRoom;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setTreasureCurr(int treasureCurr) {
        this.treasureCurr = treasureCurr;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getCharID() {
        return charID;
    }

    public int getStamina() {
        return stamina;
    }

    public int getTreasureCurr() {
        return treasureCurr;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
