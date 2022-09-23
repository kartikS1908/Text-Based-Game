package game;

public class Enemy {
    int enemyId,HP;
    String name;
    Weapon weapon;

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
