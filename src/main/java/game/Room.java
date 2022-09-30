package game;

import game.Bag.Weapon;

public class Room {
    private String name, description;
    private Weapon weapon;
    private Inventory inventory;
    private Enemy enemy;
    private int countOfTreasure;

    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public Room(){

    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of a room
     * @author Harry Li
     * @param name name of the room
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public String getDescription() {
        return description;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public Enemy getEnemy() {
        return enemy;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public int getCountOfTreasure() {
        return countOfTreasure;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public void setCountOfTreasure(int countOfTreasure) {
        this.countOfTreasure = countOfTreasure;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public Weapon getWeapon() {
        return weapon;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public Inventory getInventory() {
        return this.inventory;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * TODO: This is a example, param and return goes here.
     */
    @Override
    public String toString() {
        return this.name.substring(0, 3);
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
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
