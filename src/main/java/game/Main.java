package game;

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
            System.out.println("********************");
            Character c = new Character();
            //Map m = new Map(level);
            game = new Game();
        } else if (choice == 2) {
            game = loadGame(name);
        } else if (choice == 3){
            printRules();
            initialScreen(name);
        }
        else {
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



}
