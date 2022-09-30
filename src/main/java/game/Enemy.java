package game;

import game.Bag.Weapon;

public class Enemy {
    private int enemyId, HP;
    private String name;
    private Weapon weapon;
    private String dialogue;

    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public Enemy(int enemyId, String name, Weapon weapon,String dialogue) {
        this.enemyId = enemyId;
        this.name = name;
        this.weapon = weapon;
        this.HP = 100;
        this.dialogue = dialogue;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setHP(int HP) {
        this.HP = HP;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public Weapon getWeapon() {
        return weapon;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public int getHP() {
        return HP;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public String getName() {
        return name;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public int getEnemyId() {
        return enemyId;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void attack(Character player)
    {
        player.setHP(Math.max(player.getHP() - this.weapon.getAttack(), 0));
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public String getDialogue() { return dialogue;}
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    @Override
    public String toString()
    {
        return "EnemyID is " + enemyId + "\n" + "Name is " + name + "\n" + "Weapon details are : " + "\n" + weapon.toString() + "\n" + "HP is " + HP + "\n" + "Enemy : " + dialogue;
    }


}
