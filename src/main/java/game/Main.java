package game;


import constants.Difficulty;
import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        myRun();
    }
    /**
     * read game engine to get all game information, run initial screen to get difficulty and run main menu in Interface
     *
     * @author Jiayuan Zhu
     * @author Kartik Sharma
     */
    public static void myRun(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome!\nEnter Your Name: ");
        String name = scanner.nextLine();
        // read config
        ConfigReader configReader = new ConfigReader();
        JSONObject jsonObject = configReader.read("./src/Configs/Engine.json");
        // print initial screen
        JSONObject gameObj = initialScreen(name, jsonObject);

        Interface dashboard = new Interface(gameObj);
        dashboard.run();
    }

    /**
     * initial screen of game, player is able to start new game, load game or read rules of game
     *
     * @author Jiayuan Zhu
     * @author Xilai Wang
     * @author Harry Li
     *
     * @param name Name of Player
     * @param jsonObject game engine
     * @return get details of the game from new game or load game
     */

    public static JSONObject initialScreen(String name, JSONObject jsonObject) {
        Scanner scanner = new Scanner(System.in);
        // choose choice
        System.out.println("********************\nHi! " + name + "!");
        System.out.print("1. Start New Game \n2. Load Game \n3. Read Rules \n4. Find out all you need to know about each difficulty level \nEnter your option: ");
        String choice = scanner.nextLine();
        System.out.println("********************");
        JSONObject gameObj = null;

        // enter different choice
        if (choice.equals("1")) {
            gameObj = chooseLevel(jsonObject);
        } else if (choice.equals("2")) {
            gameObj = loadGame(name);
        } else if (choice.equals("3")) {
            printRules();
            gameObj = initialScreen(name, jsonObject);
        }
        else if(choice.equals("4")){
            getLevelInfo(chooseLevel(jsonObject));
            gameObj = initialScreen(name,jsonObject);
        }
            else {
            // Invalid choice, recurse the function
            System.out.println("******************** \nNo Choice, Please choose again \n********************");
            gameObj = initialScreen(name, jsonObject);
        }
        return gameObj;
    }

    /**
     * choose the level of game
     *
     * @author Jiayuan Zhu
     * @author Xilai Wang
     * @author Harry Li
     *
     * @param jsonObject game engine
     * @return get details of the game according to chosen level
     */
    public static JSONObject chooseLevel(JSONObject jsonObject) {
        Scanner scanner = new Scanner(System.in);
        JSONObject gameObj;
        // choose level
        System.out.print("******************** \nChoose Level of Difficulty\n1. Easy \n2. Medium\n" +
                "3. Hard \nEnter your option: ");
        String level = scanner.nextLine();
        gameObj = (JSONObject) jsonObject.get("GAME");
        // get game details from engine
        switch (level){
            case "1" -> {
                gameObj = (JSONObject) gameObj.get("EASY");
            }
            case "2" -> {
                gameObj = (JSONObject) gameObj.get("MEDIUM");
            }
            case "3" -> {
                gameObj = (JSONObject) gameObj.get("HARD");
            }
            default -> {
                // Invalid choice, recurse the function
                System.out.println("******************** \nNo Choice, Please choose again \n********************");
                gameObj = chooseLevel(jsonObject);
            }
        }
        System.out.println("********************");
        return gameObj;
    }
    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
     public static void getLevelInfo(JSONObject jsonObject)
     {
         System.out.println("Grid Dimensions are " + jsonObject.get("X-size") + " x " + jsonObject.get("Y-size") );
         System.out.println("Number of rooms is " + jsonObject.get("numRooms"));
         System.out.println("You can choose any one of the following characters :");
         JSONArray charArr = (JSONArray) jsonObject.get("listCharIDs");
         for (Object o : charArr)
         {
             String charID = (String) o;
             JSONArray charInfo = (JSONArray) jsonObject.get(charID);
             JSONArray weaponArr = (JSONArray) jsonObject.get(charInfo.get(1));
             System.out.println("Character Name : " + charInfo.get(0) + ", Dialogue is : " + charInfo.get(2) + ", Default Weapon is " + weaponArr.get(0) + ", It does " + weaponArr.get(1) + " damage.");
         }
         int numRooms = Integer.parseInt((String) jsonObject.get("numRooms"));
         for(int i = 0; i<numRooms ; i++)
         {
             int x = -1, y = -1;
             JSONArray array = (JSONArray)jsonObject.get("room"+ (i + 1));
             System.out.println("room"+ (i + 1) + " :");
             for (Object o : array) {
                 String str = (String) o;
                 if(str.charAt(0)=='r')
                     System.out.println("Room Name is " + str.substring(9));
                 else if(str.charAt(0)=='e')
                 {
                     if(str.substring(6).equals("nil")) System.out.println("No enemy in this room");
                     else {
                         JSONArray enemyArr = (JSONArray) jsonObject.get(str.substring(6));
                         JSONArray weaponArr = (JSONArray) jsonObject.get(enemyArr.get(1));
                         System.out.println("Enemy Name is " + enemyArr.get(0) + ", They say " + enemyArr.get(2) + ", They posses the " + (String) weaponArr.get(0) + ", It does " + weaponArr.get(1) + " damage.");

                     }
                 }
                 else if(str.charAt(0)=='t') {
                     if(str.substring(9).equals("nil")) System.out.println("No treasure here.");
                     else System.out.println("Treasure you can find in this room is " + str.substring(9));

                 }
                 else if(str.charAt(0)=='w')
                 {
                     if(str.substring(7).equals("nil")) System.out.println("No weapon in this room");
                     else
                     {
                         JSONArray weaponArr = (JSONArray) jsonObject.get(str.substring(7));
                         System.out.println("Weapon name is " + weaponArr.get(0) + ". It does " + weaponArr.get(1) + " damage.");
                     }
                 }
                 else if(str.charAt(0)=='i')
                 {
                     if(str.substring(10).equals("nil")) System.out.println("No inventory item here.");
                     else {
                         switch (str.substring(10)) {
                             case "h1" -> System.out.println("You can find small healing potion in this room. You can increase your HP by " + jsonObject.get(str.substring(10)));
                             case "s1" -> System.out.println("You can find small stamina booster in this room. You can increase your stamina by " + jsonObject.get(str.substring(10)));
                             case "h2" -> System.out.println("You can find big healing potion in this room. You can increase your HP by " + jsonObject.get(str.substring(10)));
                             case "s2" -> System.out.println("You can find big stamina booster in this room. You can increase your stamina by " + jsonObject.get(str.substring(10)));
                         }
                     }}
                 else {

                     if (str.charAt(0) == 'X')
                         x = Integer.parseInt(str.substring(2));
                     else if (str.charAt(0) == 'Y')
                         y = Integer.parseInt(str.substring(2));


                 }

             }
             System.out.println("Co-ordinates of the room are (" + x + "," + y + ")");
         }

     }



    /**
     * print rules of the game
     *
     * @author Jiayuan Zhu
     */
    public static void printRules() {
        System.out.println("******************** \nRules of Game");
        System.out.println("How to Win?\n" +
                "Collect 100% Treasure from Rooms in the Map!\n" +
                "If there is Enemy in Room, Player needs to beat Enemy to get the Treasure.\n" +
                "Treasure, Weapon, Inventory are distributed in Rooms.\n");
        System.out.println("Movement\n" +
                "Enter W, A, S, D to move around the map\n" +
                "Your Stamina decrease by 1 when you move to another room.\n" +
                "You are Dead if your Stamina decrease to 0. You can use Stamina Booster (Inventory) to increase Stamina.\n");
        System.out.println("Beat Enemy\n" +
                "Use Weapon to beat Enemy.\n" +
                "You are Dead if your HP decrease to 0. You can use Healing Potion (Inventory) to increase HP.\n");
        System.out.println("Bag\n" +
                "Inventory are store in bag. You can choose to drop them if it is useless.\n" +
                "Remember to use healing potion and stamina booster before you enter a room!\n" +
                "The maximum size of Bag is 5.");
        System.out.println("********************");
    }


    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public static JSONObject loadGame(String name) {
        return null;
    }


}


