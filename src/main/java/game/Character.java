package game;

import game.Bag.Weapon;

public class Character {
    String name;
    Weapon weapon;
    private boolean isAlive;
    private int maxHP;
    private int attack;
    private int defense;
    private String introduction;
    int HP,stamina, charID, treasureCurr;
    public Character(String name, String gender) {

        this.name = name;
        this.weapon = weapon;
        System.out.println("【System:】:The role was created successfully. Your name is " + name + "，Gender：" + gender);
        maxHP = 100;
        HP = maxHP;
        isAlive = true;
        attack = 10;
        defense = 10;
        System.out.println(
                "MaxHP：" + maxHP + "， current HP：" + HP + "，is alive：" + isAlive + "，Attack：" + attack + "，Defense：" + defense + "，Bag：Nothing!");
        introduction = "Individual resume：You name is " + name + "，You are" + gender ;
        System.out.println(introduction + "\n");
    }

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

}
