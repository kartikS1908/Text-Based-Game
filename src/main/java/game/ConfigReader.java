package game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    /**
     * Empty constructor
     * @author Harry Li
     * @author XIlai Wang
     */
    public ConfigReader(){

    }
    /**
     * Read the config json file in path and return a JSONObject
     * @author Harry Li
     * @author XIlai Wang
     * @return jsonObjectTop
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
