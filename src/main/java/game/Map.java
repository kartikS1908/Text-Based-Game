package game;

import constants.Difficulty;

import game.Bag.Weapon;
import globals.HandledException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Map {
    private int numOfRooms;
    private Room currentRoom;
    private Object[][] map;
    private int XMax = 0;
    private int YMax = 0;
    private JSONObject settings;

    private int[] prevPosition;
    private Character player;
    private int playerX, playerY;
    private HashMap<String, ArrayList<Integer>> roomAndCoordinates;

    /**
     * A map constructor to initiate a map object
     * @author Harry Li
     * @author Xilai Wang
     * @param settings a json object read from the game engine
     * @param player a current player object
     */
    public Map(JSONObject settings, Character player) {
        this.settings = settings;
        this.numOfRooms = Integer.parseInt(settings.get("numRooms").toString());
        int xSize = Integer.parseInt(settings.get("X-size").toString());
        int ySize = Integer.parseInt(settings.get("Y-size").toString());
        this.XMax = xSize;
        this.YMax = ySize;
        this.player = player;
        /*
         * Due to difference in the array indexing convention and our perception with Cartesian coordinate system,
         * we assign the player's X coordinate to playerY variable and the player's Y coordinate to playerX variable
         * in the Map class so that the code is more intuitive when dealing with change of position or movement which
         * also assists in future maintenance.
         * */
        this.playerX = player.getCurrentY();
        this.playerY = player.getCurrentX();
        this.map = createMap(xSize, ySize);
        this.roomAndCoordinates = getRoomPos();
        printMap();
    }

    /**
     * Create a two-dimension array to represent the map
     * @author Harry Li
     * @author Xilai Wang
     * @param xSizeOfGrid height of the map
     * @param ySizeOfGrid width of the map
     * @return Object[][] the 2d array for the map
     */
    private Object[][] createMap(int xSizeOfGrid, int ySizeOfGrid) {
        Object[][] map = new Object[xSizeOfGrid][ySizeOfGrid];
        fillMap(map);
        int x = this.playerX;
        int y = this.playerY;
        map[x][y] = "￣▽￣";
        this.prevPosition = new int[]{x, y};
        return map;
    }

    /**
     * Print the map
     * @author Harry Li
     * @author Xilai Wang
     */
    public void printMap(){
        updateMap();
        for(Object[] row : this.map){
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Update the map
     * @author Harry Li
     * @author Xilai Wang
     */
    private void updateMap(){
        updatePlayerPosition();
        this.map[this.prevPosition[0]][this.prevPosition[1]] = "---";
        this.map[this.playerX][this.playerY] = "￣▽￣";
        this.prevPosition = new int[]{this.playerX, this.playerY};
    }
    /**
     * Return the height of the map
     * @author Harry Li
     * @author Xilai Wang
     * @return int the height of the map
     */
    public int getXMax() {
        return XMax;
    }
    /**
     * Return the length of the map
     * @author Harry Li
     * @author Xilai Wang
     * @return int the length of the map
     */
    public int getYMax() {
        return YMax;
    }
    /**
     * Update the player position in the map
     * @author Harry Li
     * @author Xilai Wang
     */
    private void updatePlayerPosition(){
        this.playerX = player.getCurrentY();
        this.playerY = player.getCurrentX();
    }
    /**
     * Get the current position in the map
     * @author Harry Li
     * @author Xilai Wang
     * @return Object the current position
     */
    public Object getCurrentPosition(){
        updatePlayerPosition();
        Object currentPosition = this.map[this.playerX][this.playerY];
        if (currentPosition instanceof Room) {
            return currentPosition;
        }
        return null;
    }
    /**
     * Fill the map with "---" and Room for the empty map created before
     * @author Harry Li
     * @author Xilai Wang
     * @param map the empty map created before
     */
    private void fillMap(Object[][] map){
        for(int i = 0; i < map.length; i++){
            Arrays.fill(map[i], "---");
        }

        for (int i = 0; i < this.numOfRooms; i++) {
            JSONArray array = (JSONArray) this.settings.get("room" + (i + 1));
            Room room = new Room();
            int x = 0, y = 0;
            try {
                // fill a room based on the settings
                for (Object obj : array) {
                    String str = (String) obj;

                    switch (str.charAt(0)) {
                        case 'r' -> room.setName(str.substring(9));
                        case 'e' -> {
                            if (str.substring(6).equals("nil")) {
                                room.setEnemy(null);
                            } else {
                                JSONArray enemyArr = (JSONArray) this.settings.get(str.substring(6));
                                JSONArray weaponArr = (JSONArray) this.settings.get(enemyArr.get(1));

                                Weapon weapon = new Weapon(weaponArr.get(0).toString(), i + 1, Integer.parseInt(weaponArr.get(1).toString()));
                                Enemy enemy = new Enemy(i + 1, enemyArr.get(0).toString(), weapon, enemyArr.get(2).toString());

                                room.setEnemy(enemy);
                            }
                        }
                        case 't' -> {
                            if (!(str.substring(9).equals("nil"))) {
                                room.setCountOfTreasure(Integer.parseInt(str.substring(9)));
                            }
                        }
                        case 'w' -> {
                            if (!(str.substring(7).equals("nil"))) {
                                JSONArray weaponArr = (JSONArray) this.settings.get(str.substring(7));
                                Weapon weapon = new Weapon(weaponArr.get(0).toString(), Integer.parseInt(str.substring(8)), Integer.parseInt(weaponArr.get(1).toString()));
                                room.setWeapon(weapon);
                            }
                        }
                        case 'i' -> {
                            if (!(str.substring(10).equals("nil"))) {
                                String item = str.substring(10);
                                int amount = Integer.parseInt(this.settings.get(item).toString());
                                room.setInventory(new Inventory(item, amount));
                            }
                        }
                        case 'X' -> {
                            x = Integer.parseInt(str.substring(2));
                            try {
                                if (x >= this.XMax) {
                                    throw new HandledException("****** X coordinate exceeds grid/map limit ******");
                                }
                            } catch (HandledException e) {
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            }

                        }
                        case 'Y' -> {
                            y = Integer.parseInt(str.substring(2));
                            try {
                                if (y >= this.YMax) {
                                    throw new HandledException("****** Y coordinate exceeds grid/map limit ******");
                                }
                            } catch (HandledException e) {
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                    if (map[x][y] instanceof Room) {
                        throw new HandledException("Some rooms are set in the SAME position! Room " + ((Room)map[x][y]).getName() + " and Room " + room.getName());
                    }
                }
                // put the room into the map
                map[x][y] = room;
            } catch (HandledException error){
                error.printStackTrace();
                System.exit(0);
            }
        }

    }
    /**
     * Return the total number of rooms in the map
     * @author Harry Li
     * @author Xilai Wang
     * @return int the number of rooms
     */
    public int getNumOfRooms() {
        return numOfRooms;
    }
    /**
     * Set the total number of rooms
     * @author Harry Li
     * @author Xilai Wang
     * @param numOfRooms the current total number of rooms
     */
    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
    /**
     * Return the two-dimension array for the map
     * @author Harry Li
     * @author Xilai Wang
     * @return Object[][] the two-dimension array for the map
     */
    public Object[][] getMap() {
        return this.map;
    }


    /**
     * room and the coordinates associated it.
     *
     * @return HashMap<String, ArrayList < Integer>> roomAndCoordinates : Hashmap with room names as keys and their coordinates as objects associated to each key.
     * @author Kartik
     */
    public HashMap<String, ArrayList<Integer>> getRoomAndCoordinates() {
        return this.roomAndCoordinates;
    }

    /**
     * Setter method for roomAndCoordinates
     *
     * @param roomAndCoordinates : Hashmap with room names as keys and their coordinates as objects associated to each key.
     * @author Kartik Sharma
     */
    public void setRoomAndCoordinates(HashMap<String, ArrayList<Integer>> roomAndCoordinates) {
        this.roomAndCoordinates = roomAndCoordinates;
    }

    /**
     * Get the position of each room on the map.
     *
     * @return HashMap<String, ArrayList < Integer>> : All the rooms and their coordinates.
     * @author Kartik Sharma
     */
    public HashMap<String, ArrayList<Integer>> getRoomPos()  //initializer for the rooms on the map.
    {
        HashMap<String, ArrayList<Integer>> myData = new HashMap<>();

        for (int i = 0; i < numOfRooms; i++) {
            JSONArray roomArray = (JSONArray) settings.get("room" + (i + 1));
            Integer X = Integer.parseInt(((String) roomArray.get(5)).substring(2));
            Integer Y = Integer.parseInt(((String) roomArray.get(6)).substring(2));
            String roomName = ((String) roomArray.get(0)).substring(9);
            ArrayList<Integer> coordinates = new ArrayList<>();
            coordinates.add(X);
            coordinates.add(Y);
            myData.put(roomName, coordinates);
        }
        return myData;
    }
}
