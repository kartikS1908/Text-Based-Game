package game;

import game.Bag.Bag;
import game.Bag.CMUtility;
import game.Bag.Weapon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;
    private JSONObject gameObj;
    Character player;
    Map map;
    int loadFlag = 0; //Set if current game has been loaded from the load file.

    /**
     * get game OBJ
     *
     * @author Dehao Liu
     * @author Harry Li
     * @author Xilai Wang
     *
     */
    public Interface(JSONObject gameObj) {
        this.gameObj = gameObj;
        this.scanner = new Scanner(System.in);

    }

    /**
     * Empty constructor for interface.
     *
     * @author Kartik Sharma
     */
    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * setter method for the player.
     *
     * @author Kartik Sharma
     */
    public void setPlayer() {
        player = Character.createChar(this.gameObj);
    }

    /**
     * setter method for the map.
     *
     * @author Kartik Sharma
     */
    public void setMap() {
        map = new Map(this.gameObj, player);
    }

    /**
     * Character interface
     *
     * @author Dehao Liu
     * @author Harry Li
     * @author Xilai Wang
     * @author Jiayuan Zhu
     * @author Kartik Sharma
     *
     */
    public void run() throws IOException {
        /* Configure Player Information Start */
        if (loadFlag == 0) {
            setPlayer();
            setMap();
        }
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
            System.out.println("5 Exit");
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
                case '4': {
                    saveGame(player, map);
                    Main.myRun();
                }
                case '5':
                    System.out.println("(Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isFlag = false;
                        Main.myRun();
                    }
                    if (isExit == 'N')
                        break;

            }

        }

    }


    /**
     * TODO: what this function do.
     *
     * Move the player in interaction
     * @author Harry Li
     * @author Xilai Wang
     * @param player the current player
     * @param map the current map
     */

    public void movePlayer(Character player, Map map) throws IOException {
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
     * Interact with various items inside the room.
     *
     * @param room   : Room that the player has entered.
     * @param player : current character.
     * @author Kartik Sharma
     */
    public void interact(Room room, Character player) throws IOException {
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
            System.out.println("Your current weapon details:");
            System.out.println(player.getWeapon().toString());
            System.out.println("You can swap your current weapon for:");
            System.out.println(room.getWeapon().toString());
            System.out.println("Press 1 to swap press any other key to ignore.");
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
                loadFlag = 0;
                System.out.println("You have died please try a new game!!!!");
                Main.myRun();
            }
        } else player.setTreasureCurr(player.getTreasureCurr() + room.getCountOfTreasure());


        if (player.getTreasureCurr() == 100) {
            loadFlag = 0;
            System.out.println("You have conquered this map try a harder difficulty. If already done with the hard level then you have mastered this game.");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            Main.myRun();
        }

    }

    /**
     * TODO: what this function do.
     *
     * @author Harry Li
     * @author Dehao Liu
     * @author Kartik Sharma
     * @author Xilai Wang
     * @author Jiayuan Zhu
     * TODO: This is a example, param and return goes here.
     */
    public void interactWithBag(Character player) {
        Bag bag = player.getBag();
        System.out.println(bag.toString());
        if (bag.getInventories().size() != 0) {
            System.out.println("Do you want to use (1) or drop (2) an item in the bag?");
            int choice = scanner.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("Wrong choice, please try again!");
                choice = scanner.nextInt();
            }
            if (choice == 1) {
                System.out.println("Enter the number to use an item");
                int choiceOfIndex = scanner.nextInt();
                while (!bag.checkIsIndexValid(choiceOfIndex)) {
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
                while (!bag.checkIsIndexValid(choiceOfIndex)) {
                    System.out.println("Wrong number, please again!");
                    System.out.println("Enter the number to drop an item");
                    choiceOfIndex = scanner.nextInt();
                }

                bag.dropInventory(choiceOfIndex);
            }
        }


    }

    /**
     * Save game in its current state
     *
     * @param player : Current character.
     * @param map    : Current map.
     * @author Kartik Sharma
     */
    public void saveGame(Character player, Map map) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the file you want to save to");
        String nameOfFile = scanner.nextLine();
        String path = "./src/Configs/"+nameOfFile+".json";
        JSONObject json = new JSONObject();
        json = this.gameObj;
        json.put("ID", player.getCharID());
        json.put("Weapon", player.getWeapon().getID());
        json.put("HP", player.getHP());
        json.put("Stamina", player.getStamina());
        json.put("Y", player.getCurrentX());
        json.put("X", player.getCurrentY());
        json.put("Treasure", player.getTreasureCurr());
        json.put("Bag", player.getBag().getIDs());
        ArrayList<String> roomRequiredDelete = new ArrayList<>(); //rooms that have already been visited.
        ArrayList<String> roomRequiredKeep = new ArrayList<>(); //rooms that are still on the map.

        File file=new File(path);
        file.createNewFile();

        for (int i = 0; i < Integer.parseInt(String.valueOf(json.get("numRooms"))); i++) {
            JSONArray roomArr = (JSONArray) json.get("room" + (i + 1));
            if (!map.getRoomAndCoordinates().containsKey((String.valueOf(roomArr.get(0))).substring(9))) {
                roomRequiredDelete.add("room" + (i + 1));
            } else roomRequiredKeep.add("room" + (i + 1));
        }

        //remove visited rooms from save file.
        for (String elem : roomRequiredDelete) {
            json.remove(elem);

        }
        json.put("numRooms", map.getNumOfRooms());
        // change index of rooms remaining in the save file
        for (int i = 0; i < (Integer) json.get("numRooms"); i++) {
            if (!roomRequiredKeep.get(i).equals("room" + (i + 1))) {
                json.put("room" + (i + 1), json.get(roomRequiredKeep.get(i)));
                json.remove(roomRequiredKeep.get(i));
            }
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Load game from the save file.
     *
     * @author Kartik Sharma
     */

    public void loadGame() throws IOException {
        String directory = "./src/Configs";
        File file = new File(directory);
        String[] fileNameList = file.list();
        ArrayList<String> names = new ArrayList<>();
        for(String elem : fileNameList)
        {
            String str = elem.substring(0,elem.length()-5);
            names.add(str);
        }
        names.remove("Engine");
        System.out.println("List of save you can choose from");
        for(String elem : names)
            System.out.println(elem);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the file you want to read from(please enter a name from the file list)");
        String nameOfFile = scanner.nextLine();
        String path = "./src/Configs/"+nameOfFile+".json";
        ConfigReader configReader = new ConfigReader();
        JSONObject object = configReader.read(path);
        this.gameObj = object;
        int HP = Math.toIntExact((long) object.get("HP"));
        int treasure = Math.toIntExact((long) object.get("Treasure"));
        int stamina = Math.toIntExact((long) object.get("Stamina"));
        int x = Math.toIntExact((long) object.get("X"));
        int y = Math.toIntExact((long) object.get("Y"));
        int maxHP = Integer.parseInt((String) object.get("Max_HP"));
        int playerID = Math.toIntExact((long) object.get("ID"));
        String weaponID = "w" + Math.toIntExact((long) object.get("Weapon"));
        String playerName = (String) ((JSONArray) object.get("ch" + playerID)).get(0);
        String playerDialogue = (String) ((JSONArray) object.get("ch" + playerID)).get(2);
        JSONArray weaponArr = (JSONArray) object.get(weaponID);
        Weapon weapon = new Weapon(weaponArr.get(0).toString(), Integer.parseInt(weaponID.substring(1)), Math.toIntExact((long) weaponArr.get(1)));
        JSONArray bagArr = (JSONArray) object.get("Bag");
        Bag bag = new Bag(5);
        for (Object elem : bagArr) {
            long value = (long) object.get(elem);
            int val = Math.toIntExact(value);
            bag.addInventory(new Inventory(elem.toString(), val));
        }
        player = new Character(playerName, weapon, playerID, playerDialogue,maxHP);
        player.setHP(HP);
        player.setTreasureCurr(treasure);
        player.setStamina(stamina);
        player.setCurrentPosition(y, x);
        player.setBag(bag);
        map = new Map(object, player);

        //identifier for game initialization from a save file
        loadFlag = 1;
        run();

    }

}
