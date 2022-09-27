package game;

import game.Bag.Weapon;

public class Enemy {
    int enemyId, HP;
    String name;
    Weapon weapon;

    public Enemy(int enemyId, String name, Weapon weapon) {
        this.enemyId = enemyId;
        this.name = name;
        this.weapon = weapon;
        this.HP = 100;
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


}
