package game;

import game.Bag.Bag;
import game.Bag.CMUtility;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;
    private JSONObject gameObj;

    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * @author Harry Li
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public Interface(JSONObject gameObj) {
        this.gameObj = gameObj;
        this.scanner = new Scanner(System.in);
    }

    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * @author Harry Li
     * @author Xilai Wang
     * @author Jiayuan Zhu
     * TODO: This is a example, param and return goes here.
     */
    public void run() {
        /* Configure Player Information Start */
        Character player = Character.createChar(this.gameObj); // Create a character
        Map map = new Map(this.gameObj, player);

        /* Configure Player Information End */

        System.out.println(player.getName() + "，Welcome to game！");
        System.out.println("The game method：input w(UP) s(down) a(left) d(right) Move around the map。");
        System.out.println("(～￣▽￣)～");
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("--------Welcome--------");
            System.out.println("1 Open bag");
            System.out.println("2 View map");
            System.out.println("3 Move player");
            System.out.println("4 Save & Quit");
            System.out.println("4 Exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    interactWithBag(player);
                    break;
                case '2':
                    map.printMap();
                    break;
                case '3':
                    movePlayer(player, map);
                    break;
                case '4':{
                    saveGame(player,map);
                    Main.myRun();
                }
                case '5':
                    System.out.println("(Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isFlag = false;
                    }
                    break;
            }

        }

    }

    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */

    public void movePlayer(Character player, Map map) {
        System.out.println("You can move by W (up), S (down), A (left), D (right)");
        String direction = this.scanner.next();
        Movement currentMove = new Movement(player, map);
        currentMove.move(direction);

        if (player.getStamina() <= 0) {
            System.out.println("You run out of stamina please try a new game!!!!");
            Main.myRun();
        }

        Object currentPosition = map.getCurrentPosition();
        if (currentPosition != null) {
            map.setNumOfRooms(map.getNumOfRooms() - 1);
            Room currentRoom = (Room) currentPosition;
            map.getRoomAndCoordinates().remove(currentRoom.getName());
            map.setRoomAndCoordinates(map.getRoomAndCoordinates());
            interact(currentRoom, player);
            System.out.println(map.getRoomAndCoordinates());
        }
        map.printMap();
    }

    /**
     * TODO: what this function do.
     * @author Kartik Sharma
     * TODO: This is a example, param and return goes here.
     */
    public void interact(Room room, Character player) {
        System.out.println("Welcome to " + room.getName());

        if (room.getInventory() != null) {
            Inventory inventory = room.getInventory();
            System.out.println("Inventory item in this room is : ");
            System.out.println(inventory.getDes());
            System.out.println("Press 1 to put this inventory into your bag OR any other key to ignore.");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println(inventory.getName());
                player.getBag().addInventory(inventory);
            }
        }
        if (room.getWeapon() != null) {
            System.out.println(room.getWeapon().toString());
            System.out.println("Press 1 to use press any other key to ignore.");
            int getWeapon = scanner.nextInt();
            if (getWeapon == 1) {
                player.setWeapon(room.getWeapon());
                System.out.println(player.weapon.toString());
            }

        }
        if (room.getEnemy() != null) {

            Enemy enemy = room.getEnemy();
            System.out.println("--------------------------------------------------------------");
            System.out.println("ENEMY: I have been waiting for you. My name is " + enemy.getName() + " " + enemy.getDialogue());
            System.out.println("YOU: " + player.getDialogue());

            System.out.println("Enemy : [attacks]");
            enemy.attack(player);
            System.out.println("Your HP is now " + player.getHP());

            while (player.getHP() != 0 && enemy.getHP() != 0) {

                System.out.println("Press 1 to attack if you press any other key your enemy will attack again.");
                int option = scanner.nextInt();
                if (option == 1) {
                    if (player.getHP() != 0) {
                        player.attack(enemy);
                        System.out.println("Enemy's HP is now " + enemy.getHP());
                    }
                }
                if (enemy.getHP() != 0) {
                    System.out.println("Enemy : [attacks]");
                    enemy.attack(player);
                    System.out.println("Your HP is now " + player.getHP());
                }


            }

            if (enemy.getHP() == 0) {
                System.out.println("You were victorious in this battle collect treasure and move to next battle");
                System.out.println("Press 1 to collect treasure (if treasure in this room is 0 press any key.)");
                int option = scanner.nextInt();
                if (option == 1)
                    player.setTreasureCurr(player.getTreasureCurr() + room.getCountOfTreasure());
                System.out.println("Your current HP is : " + player.getHP());
                System.out.println("Your treasure is " + player.getTreasureCurr());
            }

            if (player.getHP() == 0) {
                System.out.println("You have died please try a new game!!!!");
                Main.myRun();
            }
        }
        else player.setTreasureCurr(player.getTreasureCurr() + room.getCountOfTreasure());


        if (player.getTreasureCurr() == 100) {

            System.out.println("You have conquered this map try a harder difficulty. If already done with the hard level then you have mastered this game.");
            Main.myRun();
        }

    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Dehao Liu
     * @author Kartik Sharma
     * @author Xilai Wang
     * @author Jiayuan Zhu
     * TODO: This is a example, param and return goes here.
     */
    public void  interactWithBag(Character player)  {
        Bag bag = player.getBag();
        System.out.println(bag.toString());
        if(bag.getInventories().size() != 0){
            System.out.println("Do you want to use (1) or drop (2) an item in the bag?");
            int choice = scanner.nextInt();
            while(choice != 1 && choice != 2){
                System.out.println("Wrong choice, please try again!");
                choice = scanner.nextInt();
            }
            if(choice == 1){
                System.out.println("Enter the number to use an item");
                int choiceOfIndex = scanner.nextInt();
                while (!bag.checkIsIndexValid(choiceOfIndex)){
                    System.out.println("Wrong number, please again");
                    System.out.println("Enter the number to use an item");
                    choiceOfIndex = scanner.nextInt();
                }

                player.useInventory(choiceOfIndex);
                System.out.println("Your Hp is " + player.getHP()
                        + " Your stamina is " + player.getStamina());
            } else {
                System.out.println("Enter the number to drop an item");
                int choiceOfIndex = scanner.nextInt();
                while (!bag.checkIsIndexValid(choiceOfIndex)){
                    System.out.println("Wrong number, please again!");
                    System.out.println("Enter the number to drop an item");
                    choiceOfIndex = scanner.nextInt();
                }

                bag.dropInventory(choiceOfIndex);
            }
        }


    }

    public static void saveGame(Character player,Map map) {
        String path = "./src/Configs/saveFile.json";
        JSONObject json = new JSONObject();
        json.put("ID",player.getCharID());
        json.put("Weapon",player.getWeapon().getID());
        json.put("HP",player.getHP());
        json.put("Stamina",player.getStamina());
        json.put("Y",player.getCurrentX());
        json.put("X",player.getCurrentY());
        json.put("Treasure",player.getTreasureCurr());
        json.put("Bag",player.getBag().getIDs());
        json.put("numRooms",map.getNumOfRooms());
        json.put("rooms",map.getRoomAndCoordinates());
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
