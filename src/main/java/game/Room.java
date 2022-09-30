package game;

import game.Bag.Weapon;

public class Room {
    private String name, description;
    private Weapon weapon;
    private Inventory inventory;
    private Enemy enemy;
    private int countOfTreasure;

    /**
     * Room constructor to initiate a Room object
     * @author Harry Li
     */
    public Room(){

    }
    /**
     * Return the name of a room
     * @author Harry Li
     * @return String the name of a room
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of a room
     * @author Harry Li
     * @param name the current name of a room
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Return the enemy in a room
     * @author Harry Li
     * @return Enemy the enemy in a room
     */
    public Enemy getEnemy() {
        return enemy;
    }
    /**
     * Set a new enemy in a room
     * @author Harry Li
     * @return Enemy the enemy to be set in a room
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    /**
     * Return the count of treasures in a room
     * @author Harry Li
     * @return int the count of treasures in a room
     */
    public int getCountOfTreasure() {
        return countOfTreasure;
    }
    /**
     * Set the current count of treasures in a room
     * @author Harry Li
     * @param countOfTreasure the current count of treasures in a room
     */
    public void setCountOfTreasure(int countOfTreasure) {
        this.countOfTreasure = countOfTreasure;
    }
    /**
     * Return the weapon in a room
     * @author Harry Li
     * @return Weapon the weapon in a room
     */
    public Weapon getWeapon() {
        return weapon;
    }
    /**
     * Set the weapon in a room
     * @author Harry Li
     * @param weapon the new weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    /**
     * Return the inventory in a room
     * @author Harry Li
     * @return Inventory the inventory in a room
     */
    public Inventory getInventory() {
        return this.inventory;
    }
    /**
     * Set the inventory in a room
     * @author Harry Li
     * @param inventory the new inventory in a room
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    /**
     * Override toString method so that it returns the first three letters of a room's name
     * @author Harry Li
     * @return String the first three letters of a room's name
     */
    @Override
    public String toString() {
        return this.name.substring(0, 3);
    }
}
