package game;


import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.print("Enter Your Name:");
        String name = scanner.nextLine();
        Game game = initialScreen(name);


    }

    public static Game initialScreen(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************\nHi! " + name + "!");
        System.out.print("1. Start New Game \n2. Load Game \n3. Read Rules \nEnter your option:");
        int choice = scanner.nextInt();
        System.out.println("********************");
        Game game = null;
        if (choice == 1) {
            System.out.print("******************** \nChoose Level of Difficulty\n1. Simple \n2. Medium\n" +
                    "3. Difficult \nEnter your option:");
            int level = scanner.nextInt();

            if(level==1)  readConfigs(1);
            else if(level==2)  readConfigs(2);
            else if(level==3)  readConfigs(3);

            System.out.println("********************");
            Character c = new Character();
            //Map m = new Map(level);
            game = new Game();
        } else if (choice == 2) {
            game = loadGame(name);
        } else if (choice == 3) {
            printRules();
            initialScreen(name);
        } else {
            System.out.println("******************** \nNo Choice, Please choose again \n********************");
            initialScreen(name);
        }
        return game;
    }

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
                "The maximum size of Bag is 4.");
        System.out.println("********************");
    }

    public static Game loadGame(String name) {
        return null;
    }

    public static void readConfigs(int difficulty)
    {
        JSONParser parser = new JSONParser();
        try {

            Object obj =  parser.parse(new FileReader("./src/Configs/Engine.json"));
            JSONObject jsonObjectTop = (JSONObject) obj;
            JSONObject jsonObjectLevel = (JSONObject)jsonObjectTop.get("GAME");
            JSONObject jsonObject;
            if(difficulty==1) {
                jsonObject = (JSONObject) jsonObjectLevel.get("EASY");
                System.out.println("EASY LEVEL:");
            }
            else if(difficulty==2)
            {
                jsonObject = (JSONObject) jsonObjectLevel.get("MEDIUM");
                System.out.println("MEDIUM LEVEL:");
            }

            else if(difficulty==3)
            {
                jsonObject = (JSONObject) jsonObjectLevel.get("HARD");
                System.out.println("HARD LEVEL:");
            }

            else {
                System.out.println("INVALID DIFFICULTY LEVEL");
                return;}

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
                }
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
    }


