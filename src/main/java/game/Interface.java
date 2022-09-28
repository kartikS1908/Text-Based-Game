package game;

import constants.Difficulty;
import game.Bag.Bag;
import game.Bag.CMUtility;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    private JSONObject gameObj;
    public Interface(JSONObject gameObj){
        this.gameObj = gameObj;
    }
    public void run(){
        /* Configure Player Information Start */
        System.out.println("(｡･∀･)ﾉﾞHi ！Welcome to the game！please create your Character (｡･∀･)ﾉ");
        System.out.print("Warrior, please enter your name：");
        Scanner scanner = new Scanner(System.in); // Create a Scanner for Initialization
        String inputName = scanner.next();
        System.out.print("Warriors, please enter your gender (Male/Female) : ");
        String inputGender = scanner.next();
        inputGender = inputGender.toLowerCase();

        if (!(inputGender.equals("male") || inputGender.equals("female"))) {
            System.out.println("Wrong! try again!");
            System.exit(0);
        }

        Character player = new Character(inputName, inputGender, 0, 0); // Generate a player
        Map map = new Map(this.gameObj, player);

        List<String> bag = new ArrayList<String>(); // Generate the bag of the player
        int hasCount = 1; // Set Default Counter
        /* Configure Player Information End */

        System.out.println(player.getName() + "，Welcome to game！");
        System.out.println("The game method：input w(UP) s(down) a(left) d(right) Move around the map。");
        System.out.println("(～￣▽￣)～");
        boolean isFlag =true;
        while (isFlag){
            System.out.println("--------Welcome--------");
            System.out.println("1 Open bag");
            System.out.println("2 View map");
            System.out.println("3 Move player");
            System.out.println("4 Use healing potion");
            System.out.println("5 Exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu){
                case '1':
//                    Bag.main(args);
                    break;
                case '2':
                    map.printMap();
                    break;
                case '3':
                    System.out.println("You can move by W (up), S (down), A (left), D (right)");
                    String direction = scanner.next();
                    int currentX = player.getCurrentX();
                    int currentY = player.getCurrentY();
                    switch (direction.toLowerCase()) {
                        case "s" -> {
                            if (currentX + 1 >= map.getXMax()){
                                System.out.println("Warning! You will cross the boundary");
                            }else{
                                currentX++;
                            }
                        }
                        case "w" -> {
                            if (currentX - 1 < 0){
                                System.out.println("Warning! You will cross the boundary");
                            }else{
                                currentX--;
                            }
                        }
                        case "a" -> {
                            if (currentY - 1 < 0){
                                System.out.println("Warning! You will cross the boundary");
                            }else{
                                currentY--;
                            }
                        }
                        case "d" -> {
                            if (currentY + 1 >= map.getYMax()){
                                System.out.println("Warning! You will cross the boundary");
                            }else{
                                currentY++;
                            }
                        }

                    }
                    player.setCurrentPosition(currentX, currentY);
                    map.printMap();
                    break;
                case '4':

                    break;
                case '5':
                    System.out.println("(Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit =='Y'){
                        isFlag=false;
                    }
                    break;
            }

        }

    }
}
