package game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    private String invName;
    private int amount;

    public Inventory(String itemName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj =  parser.parse(new FileReader("./src/Configs/Engine.json"));
        JSONObject jsonObjectTop = (JSONObject) obj;

        if (itemName == "h1") {
            this.invName = "smallHealth";
            this.amount = (int)jsonObjectTop.get("h1");;
        }
        else if (itemName == "h2") {
            this.invName = "bigHealth";
            this.amount = (int)jsonObjectTop.get("h2");;
        }
        else if (itemName == "s1") {
            this.invName = "smallStamina";
            this.amount = (int)jsonObjectTop.get("s1");;
        }
        else{
            this.invName = "bigStamina";
            this.amount = (int)jsonObjectTop.get("s2");;
        }
    }

    /**
     * Get the amount of Inventory
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Get tha name of Inventory
     *
     * @return the name
     */
    public String getName() {
        return invName;
    }

    /**
     * Get the name and usage of Inventory
     *
     * @return the name and usage
     */
    public String getDes() {
        return switch (this.invName) {
            case "smallHealth" -> "Small Healing Potion: Add "+ this.amount+ " HP";
            case "bigHealth" -> "Big Healing Potion: Add "+ this.amount+ " HP";
            case "smallStamina" -> "Small Stamina Booster: Add "+ this.amount+ " Stamina";
            case "bigStamina" -> "Big Stamina Booster: Add "+ this.amount+ " Stamina";
        };
    }




}