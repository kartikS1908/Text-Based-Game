package game;

import game.Bag.Weapon;

public class Room {
    private String name, description;
    private Weapon weapon;
    private Inventory inventory;
    private Enemy enemy;
    private int countOfTreasure;


    public Room(String name, String description, Weapon weapon, Inventory inventory, Enemy enemy, int countOfTreasure){
        this.name = name;
        this.description = description;
        this.weapon = weapon;
        this.inventory = inventory;
        this.countOfTreasure = countOfTreasure;
        this.enemy = enemy;
    }

    public Room(){

    }

    public String getName() {
        return name;
    }

    /**
     * Set the name of a room
     * @param name name of the room
     */

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getCountOfTreasure() {
        return countOfTreasure;
    }

    public void setCountOfTreasure(int countOfTreasure) {
        this.countOfTreasure = countOfTreasure;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return this.name.substring(0, 3);
    }

    public String detailedToString()
    {
        String weaponStr,inventoryStr,enemyStr;
        if(this.weapon==null){ weaponStr = "No weapon here";}
        else weaponStr = weapon.toString();

        if(this.enemy==null) enemyStr = "No enemy here";
        else enemyStr = enemy.toString();

        if(this.inventory==null) inventoryStr = "No inventory item here";
        else inventoryStr = inventory.getDes();

        return "Room name is " + name + "\n" + "Weapon you can find in this room is :"+ "\n" + weaponStr + "\n" +
                "Inventory item in this room is : " + "\n" + inventoryStr + "\n" + "Enemy details are as follows: " + "\n" + enemyStr +
                "\n" + "Treasure amount in this room is :" + countOfTreasure;
    }
}
