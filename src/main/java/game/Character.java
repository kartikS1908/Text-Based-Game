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
     *
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     *
     * @param name name of character
     * @param weapon weapon owned by character
     * @param charID character id
     * @param dialogue dialogue
     */
    public Character(String name, Weapon weapon, int charID, String dialogue, int MaxHP) {
        this.charID = charID;
        this.name = name;
        this.weapon = weapon;
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
    }


    /**
     * player chooses character and create character
     * @author Jiayuan Zhu
     *
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
            int MaxHP = Integer.parseInt(gameObj.get("Max_HP").toString());
            Weapon w = new Weapon(String.valueOf(weapon.get(0)),Integer.parseInt(String.valueOf(character.get(1)).substring(1)),
                    Integer.parseInt(String.valueOf(weapon.get(1))));
            c = new Character(String.valueOf(character.get(0)),w,choice,String.valueOf(character.get(2)), MaxHP);
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
     * Setter method for name of character.
     * @author Kartik Sharma
     * @param name : new name of character.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for the character's weapon.
     * @author Kartik Sharma
     * @param weapon : new weapon for the character
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Setter method for HP.
     * @author Kartik Sharma
     * @param HP : new HP of character.
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * setter method for charID.
     * @author Kartik Sharma
     * @param charID : new charID.
     */
    public void setCharID(int charID) {
        this.charID = charID;
    }

    /**
     * setter method for stamina.
     * @author Kartik Sharma
     * @param stamina : new stamina for the character.
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * setter method for treasure.
     * @author Kartik Sharma
     * @param treasureCurr : new treasure amount.
     */
    public void setTreasureCurr(int treasureCurr) {
        this.treasureCurr = treasureCurr;
    }

    /**
     * getter method for name of the character.
     * @author Kartik Sharma
     * @return String name : name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for the character's HP.
     * @author Kartik Sharma
     * @return int HP : The HP of the character.
     */
    public int getHP() {
        return HP;
    }

    /**
     * getter method for the character's weapon.
     * @author Kartik Sharma
     * @return Weapon weapon : The character's weapon.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * getter method for the character's ID.
     * @author Kartik Sharma
     * @return int charID : The character's ID.
     */
    public int getCharID() {
        return charID;
    }

    /**
     * getter method for the character's stamina.
     * @author Kartik Sharma
     * @return int stamina : The character's stamina.
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * getter method for the character's current treasure.
     * @author Kartik Sharma
     * @return int treasureCurr : The character's current treasure.
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
     * set enemy's new HP after an attack.
     * @author Kartik Sharma
     * @param enemy : The enemy being attacked..
     */
    public void attack(Enemy enemy)
    {
        if (this.HP != 0) {
            enemy.setHP(Math.max(enemy.getHP() - this.weapon.getAttack(), 0));
        }
    }

    /**
     * getter method for the character's dialogue.
     * @author Kartik Sharma
     * @return String dialogue : The character's dialogue.
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
    /**
     * Set the value of bag
     * @author Xilai Wang
     * @param bag A BagList
     */
    public void setBag(Bag bag) {
        this.bag = bag;
    }
}
