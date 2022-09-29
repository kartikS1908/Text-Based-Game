package game;

import constants.Difficulty;
import game.Bag.Bag;
import game.Bag.CMUtility;
import game.Bag.Weapon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;
    private JSONObject gameObj;

    public Interface(JSONObject gameObj) {
        this.gameObj = gameObj;
        this.scanner = new Scanner(System.in);
    }

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
            System.out.println("4 Use healing potion");
            System.out.println("5 Exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1':
//                    Bag.main(args);
                    break;
                case '2':
                    map.printMap();
                    break;
                case '3':
                    movePlayer(player, map);
                    break;
                case '4':

                    break;
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

    public void movePlayer(Character player, Map map) {
        System.out.println("You can move by W (up), S (down), A (left), D (right)");
        String direction = this.scanner.next();
        Movement currentMove = new Movement(player, map);
        currentMove.move(direction);

        Object currentPosition = map.getCurrentPosition();
        if (currentPosition != null) {
            // Kartik your function goes here!
            System.out.println("***** You have entered the room ");
            int roomID = map.getRoomID(map.getPlayerX(), map.getPlayerY());
            System.out.println(roomID);
//            Room room = createRoom(roomID, map.getSettings());
            interact((Room) currentPosition, player);
//            System.out.println(((Room) currentPosition).getEnemy());
//            System.out.println(createRoom(roomID, map.getSettings()));
        }
        map.printMap();
    }


    public void interact(Room room, Character player) {

        System.out.println("Welcome to " + room.getName());

        if (room.getInventory() != null) {
            System.out.println("Inventory item in this room is : ");
            System.out.println(room.getInventory().getDes());
            System.out.println("Press 1 to use press any other key to ignore.");
            int getInventory = scanner.nextInt();
            if (getInventory == 1) {
                System.out.println(room.getInventory().getName());
                Inventory.useInv(player, room.getInventory());
                System.out.println("Now your HP is " + player.getHP() + " and your stamina is " + player.getStamina());
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
                System.out.println("Press one to collect treasure (if treasure in this room is 0 press any key.)");
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
}
