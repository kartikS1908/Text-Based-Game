package game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    /**
     * Initiate a ConfigReader object to read the game engine settings
     * @author Harry Li
     * @author XIlai Wang
     */
    public ConfigReader(){

    }
    /**
     * Read the json file with specified path
     * @author Harry Li
     * @author Xilai Wang
     * @param path the path to the json file
     * @return JSONObject the settings read from the json file as a json object
     */
    public JSONObject read(String path) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObjectTop = null;
        try {
            Object obj = parser.parse(new FileReader(path));
            jsonObjectTop = (JSONObject) obj;

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return jsonObjectTop;
    }
}
