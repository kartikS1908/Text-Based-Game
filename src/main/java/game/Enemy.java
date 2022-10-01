package game;

import game.Bag.Weapon;

public class Enemy {
    private int enemyId, HP;
    private String name;
    private Weapon weapon;
    private String dialogue;

    /**
     * Constructor for Enemy type
     * @author Kartik Sharma
     * @param enemyId : enemy ID
     * @param name: name for the enemy
     * @param weapon : enemy's weapon
     * @param dialogue : enemy's dialogue string.
     */
    public Enemy(int enemyId, String name, Weapon weapon,String dialogue) {
        this.enemyId = enemyId;
        this.name = name;
        this.weapon = weapon;
        this.HP = 100;
        this.dialogue = dialogue;
    }
    /**
     * setter method for HP.
     * @author Kartik Sharma
     * @param HP : new HP value.
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * getter method for enemy's HP.
     * @author Kartik Sharma
     * @return int HP : current HP value for enemy.
     */
    public int getHP() {
        return HP;
    }

    /**
     * getter method for enemy's name.
     * @author Kartik Sharma
     * @return String name : enemy name.
     */
    public String getName() {
        return name;
    }

    /**
     * attack function for enemy.
     * @author Kartik Sharma
     * @param player: Player that the enemy attacks.
     */
    public void attack(Character player)
    {
        player.setHP(Math.max(player.getHP() - this.weapon.getAttack(), 0));
    }

    /**
     * getter method for enemy's dialogue.
     * @author Kartik Sharma
     * @return String dialogue : The enemy's dialogue string.
     */
    public String getDialogue() { return dialogue;}

    /**
     * toString method for an enemy.
     * @author Kartik Sharma
     * @return String : String representation of Enemy.
     */
    @Override
    public String toString()
    {
        return "EnemyID is " + enemyId + "\n" + "Name is " + name + "\n" + "Weapon details are : " + "\n" + weapon.toString() + "\n" + "HP is " + HP + "\n" + "Enemy : " + dialogue;
    }


}
