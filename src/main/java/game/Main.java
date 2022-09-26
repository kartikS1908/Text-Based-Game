package game;

import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj =  parser.parse(new FileReader("./src/Configs/Engine.json"));
            JSONObject jsonObjectTop = (JSONObject) obj;
            JSONObject jsonObjectLevel = (JSONObject)jsonObjectTop.get("GAME");
            JSONObject jsonObject = (JSONObject) jsonObjectLevel.get("EASY");
            System.out.println("EASY LEVEL:");
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
