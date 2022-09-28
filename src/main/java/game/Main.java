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
     * @param name Name of Player
     * @param jsonObject game engine
     * @return get details of the game from new game or load game
     */
    public static JSONObject initialScreen(String name, JSONObject jsonObject) {
        Scanner scanner = new Scanner(System.in);
        // choose choice
        System.out.println("********************\nHi! " + name + "!");
        System.out.print("1. Start New Game \n2. Load Game \n3. Read Rules \nEnter your option: ");
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
        } else {
            // Invalid choice, recurse the function
            System.out.println("******************** \nNo Choice, Please choose again \n********************");
            gameObj = initialScreen(name, jsonObject);
        }
        return gameObj;
    }

    /**
     * choose the level of game
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
     * print rules of the game
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
                "The maximum size of Bag is 4.");
        System.out.println("********************");
    }

    public static JSONObject loadGame(String name) {
        return null;
    }


}


