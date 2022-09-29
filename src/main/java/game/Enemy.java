package game;

import game.Bag.Weapon;

public class Enemy {
    private int enemyId, HP;
    private String name;
    private Weapon weapon;
    private String dialogue;

    public Enemy(int enemyId, String name, Weapon weapon,String dialogue) {
        this.enemyId = enemyId;
        this.name = name;
        this.weapon = weapon;
        this.HP = 100;
        this.dialogue = dialogue;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void attack(Character player)
    {
        player.setHP(Math.max(player.getHP() - this.weapon.getAttack(), 0));
    }

    public String getDialogue() { return dialogue;}
    @Override
    public String toString()
    {
        return "EnemyID is " + enemyId + "\n" + "Name is " + name + "\n" + "Weapon details are : " + "\n" + weapon.toString() + "\n" + "HP is " + HP + "\n" + "Enemy : " + dialogue;
    }


}
