package game;

import game.Bag.Weapon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Character {
    String name;
    Weapon weapon;
    private boolean isAlive;
    private int maxHP;
    private String introduction;
    private String dialogue;
    int HP, stamina, charID, treasureCurr;
    private int currentX, currentY;

    /**
     * create character
     *
     * @param name name of character
     * @param weapon weapon owned by character
     * @param charID character id
     * @param dialogue dialogue
     */
    public Character(String name, Weapon weapon, int charID, String dialogue) {
        this.charID = charID;
        this.name = name;
        this.weapon = weapon;
        System.out.println("【System:】:The role was created successfully. Your name is " + name);
        this.maxHP = 100;
        this.HP = maxHP;
        this.stamina = 10;
        this.isAlive = true;
        this.currentX = 0;
        this.currentY = 0;
        this.treasureCurr = 0;

        this.dialogue = dialogue;

        this.introduction = "Individual resume：Your name is " + name + ". " + dialogue;

        System.out.println("MaxHP：" + maxHP + "， current HP：" + HP + "，is alive：" + isAlive + " Bag：Nothing!");
        System.out.println(this.introduction);
    }


    /**
     * choose character by player and create character
     *
     * @param gameObj game engine that contains details of the game
     * @return character details
     */
    public static Character createChar(JSONObject gameObj) {
        System.out.println("char" + gameObj);
        Character c = null;
        System.out.println("******************** \n(｡･∀･)ﾉﾞHi ！Welcome to the game！please select your Character (｡･∀･)ﾉ \n");
        // choose character
        JSONArray charList = (JSONArray) gameObj.get("listCharIDs");
        int index = 1;
        // print character details
        for (Object charId : charList) {
            JSONArray character = (JSONArray) gameObj.get(charId);
            JSONArray weapon = (JSONArray) gameObj.get(character.get(1));
            System.out.println("Character " + index + ": " + character.get(0) + "\nWeapon: " + weapon.get(0) + "\nDamage: " +
                    weapon.get(1) + "\n" + character.get(2) + "\n");
            index += 1;
        }
        System.out.println("All Character have same HP and Stamina");

        // get choice and create character
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Option: ");
        int choice = scanner.nextInt();
        if (choice <= index && choice > 0) {
            JSONArray character = (JSONArray) gameObj.get("ch" + choice);
            JSONArray weapon = (JSONArray) gameObj.get(character.get(1));
            Weapon w = new Weapon(String.valueOf(weapon.get(0)),Integer.parseInt(String.valueOf(character.get(1)).substring(1)),
                    Integer.parseInt(String.valueOf(weapon.get(1))));
            c = new Character(String.valueOf(character.get(0)),w,choice,String.valueOf(character.get(2)));
            System.out.println("********************");
        }
        else {
            // Invalid choice, recurse the function
            System.out.println("******************** \nNo Choice, Please choose again \n********************");
            c = createChar(gameObj);
        }
        return c;
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

    public String getIntroduction()
    {return introduction;}

    public void attack(Enemy enemy)
    {

        enemy.setHP(Math.max(enemy.getHP() - this.weapon.getAttack(), 0));

    }
    public String getDialogue()
    {
        return dialogue;
    }
    /**
     * Update character position in map
     *
     * @param currentX updated x position
     * @param currentY updated y position
     */
    public void setCurrentPosition(int currentX, int currentY){
        this.currentX = currentX;
        this.currentY = currentY;

    }
}
