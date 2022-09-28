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
    int HP, stamina, charID, treasureCurr;
    private int currentX, currentY;
    public Character(String name, String gender, int x, int y) {
        this.name = name;
        this.weapon = weapon;
        this.currentX = x;
        this.currentY = y;
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

    public Character(int charID,int stamina,String name,Weapon weapon)
    {
        this.charID = charID;
        this.stamina = stamina;
        this.name = name;
        this.weapon = weapon;
        this.HP = 100;
        this.treasureCurr = 0;
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

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentPosition(int currentX, int currentY){
        this.currentX = currentX;
        this.currentY = currentY;

    }
}
