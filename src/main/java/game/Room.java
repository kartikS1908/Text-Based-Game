package game;

public class Room {
    private String name, description;
    private Weapon weapon;
    private Inventory inventory;
    private Enemy enemy;


    public Room(String name, String description, Weapon weapon, Inventory inventory, Enemy enemy){
        this.name = name;
        this.description = description;
        this.weapon = weapon;
        this.inventory = inventory;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {
        String name = this.getName();
        return "R" + name.charAt(name.length() - 1);
    }
}
