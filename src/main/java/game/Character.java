package game;

import game.Bag.Bag;
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
    private Bag bag;

    /**
     * create character
     * @author Kartik Sharma
     * @author Jiayuan Zhu
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
        this.bag = new Bag(5);
        this.introduction = "Individual resume：Your name is " + name + ". " + dialogue;
        System.out.println("MaxHP：" + maxHP + "， current HP：" + HP + "，is alive：" + isAlive + " Bag：Nothing!");
        System.out.println(this.introduction);
    }


    /**
     * player chooses character and create character
     * @author Jiayuan Zhu
     * @param gameObj game engine that contains details of the game
     * @return character details
     */
    public static Character createChar(JSONObject gameObj) {
        Character c = null;
        System.out.println("******************** \nHi ！Welcome to the game！please select your Character \n");
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
    public void setHP(int HP) {
        this.HP = HP;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setCharID(int charID) {
        this.charID = charID;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void setTreasureCurr(int treasureCurr) {
        this.treasureCurr = treasureCurr;
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
    public int getHP() {
        return HP;
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
    public int getCharID() {
        return charID;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public int getStamina() {
        return stamina;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public int getTreasureCurr() {
        return treasureCurr;
    }
    /**
     * Return the current x position of a player
     * @author Harry Li
     * @return int current x position of a player
     */
    public int getCurrentX() {
        return currentX;
    }
    /**
     * Return the current y position of a player
     * @author Harry Li
     * @return int current y position of a player
     */
    public int getCurrentY() {
        return currentY;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public String getIntroduction()
    {return introduction;}
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void attack(Enemy enemy)
    {

        enemy.setHP(Math.max(enemy.getHP() - this.weapon.getAttack(), 0));

    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public String getDialogue()
    {
        return dialogue;
    }
    /**
     * Update character position in map
     * @author Harry Li
     * @author Xilai Wang
     * @param currentX updated x position
     * @param currentY updated y position
     */
    public void setCurrentPosition(int currentX, int currentY){
        this.currentX = currentX;
        this.currentY = currentY;

    }

    /**
     * Use inventory in its bag to add HP (h1/h2) or Stamina(s1/s2)
     *
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     *
     * @param index the index of the inventory that is used by character
     */
    public void useInventory(int index) {
        Bag bag = this.bag;
        Inventory inventory = bag.getInventories().get(index - 1);
        int currentHP = this.HP;

        if (inventory.getInvID().equals("h1") || inventory.getInvID().equals("h2")) {
            currentHP += inventory.getAmount();
            if (currentHP > this.maxHP) {
                System.out.println("You are already in Max HP, the extra heal will vanished");
                this.HP = this.maxHP;
            } else {
                this.HP = currentHP;
            }
        } else {
            this.stamina += inventory.getAmount();
        }
        bag.dropInventory(index);

    }
    /**
     * Return a player's bag
     * @author Harry Li
     * @return Bag the player's bag
     */
    public Bag getBag() {
        return bag;
    }
}
