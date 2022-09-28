package game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    public ConfigReader(){

    }
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
