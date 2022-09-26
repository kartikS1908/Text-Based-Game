package game;

import game.Bag.Bag;
import game.Bag.CMUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        /* Configure Player Information Start */
        System.out.println("(｡･∀･)ﾉﾞHi ！Welcome to the game！please create your Character (｡･∀･)ﾉ");
        System.out.print("Warrior, please enter your name：");
        Scanner s = new Scanner(System.in); // Create a Scanner for Initialization
        String inputName = s.next();
        System.out.print("Warriors, please enter your gender (Male/Female) :");
        String inputGender = s.next();
        if (inputGender.equals("Male") || inputGender.equals("Female")) {
            // Nothing happened, continue the rest of things~
        } else {
            System.out.println("Wrong! try again!");
            System.exit(0);
        }
        Character you = new Character(inputName, inputGender); // Generate a player
        List<String> bag = new ArrayList<String>(); // Generate the bag of the player
        int hasCount = 1; // Set Default Counter
        /* Configure Player Information End */

        System.out.println(you.getName() + "，Welcome to game！");
        System.out.println("The game method：input w(UP) s(down) a(left) d(right) Move around the map。");
        System.out.println("(～￣▽￣)～");
        boolean isFlag =true;
        while (isFlag){
            System.out.println("--------Welcome--------");
            System.out.println("1 open bag");
            System.out.println("2 Entry map");
            System.out.println("3 healing potion");
            System.out.println("4 ");
            System.out.println("5 exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu){
                case '1':
                    Bag.main(args);
                    break;
                case '2':

                    break;
                case '3':

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
