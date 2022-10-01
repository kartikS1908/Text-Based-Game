package game;

import constants.Difficulty;

import game.Bag.Weapon;
import globals.HandledException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
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

    /**
     * A map constructor to initiate a map object
     * @author Harry Li
     * @author Xilai Wang
     * @param settings a json object read from the game engine
     * @param player a current player object
     */
    public Map(JSONObject settings, Character player){
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
    private Object[][] createMap(int xSizeOfGrid, int ySizeOfGrid){
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
        Object currentPosition = map[this.playerX][this.playerY];
        if(currentPosition instanceof Room){
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

        for(int i = 0; i < this.numOfRooms; i++) {
            JSONArray array = (JSONArray) settings.get("room" + (i + 1));
            Room room = new Room();
            int x = 0, y = 0;
            try{
                for (Object obj : array) {
                    String str = (String) obj;


                       switch (str.charAt(0)) {
                           case 'r' -> room.setName(str.substring(9));
                           case 'e' -> {
                               if (str.substring(6).equals("nil")) {
                                   room.setEnemy(null);
                               } else {
                                   JSONArray enemyArr = (JSONArray) settings.get(str.substring(6));
                                   JSONArray weaponArr = (JSONArray) settings.get(enemyArr.get(1));

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
                                   JSONArray weaponArr = (JSONArray) settings.get(str.substring(7));
                                   Weapon weapon = new Weapon(weaponArr.get(0).toString(), i + 1, Integer.parseInt(weaponArr.get(1).toString()));
                                   room.setWeapon(weapon);
                               }
                           }
                           case 'i' -> {
                               if (!(str.substring(7).equals("nil"))) {
                                   String item = str.substring(10);
                                   int amount = Integer.parseInt(settings.get(item).toString());
                                   room.setInventory(new Inventory(item, amount));
                               }
                           }
                           case 'X' -> {
                               x = Integer.parseInt(str.substring(2));

                               if (x >= XMax) {
                                   throw new HandledException("****** X coordinate exceeds grid/map limit ******");

                               }
                           }
                           case 'Y' -> {
                               y = Integer.parseInt(str.substring(2));

                               if (y >= YMax) {
                                       throw new HandledException("****** Y coordinate exceeds grid/map limit ******");
                               }

                           }
                       }




                }
                if (map[x][y] instanceof Room) {
                    throw new HandledException("Some rooms are set in the SAME position!");
                }
                map[x][y] = room;
            }catch (HandledException error){
                String message = error.getMessage();

                if (message.contains("rooms")){
                    System.out.println(error.getMessage() + " Room " + ((Room)map[x][y]).getName() + " and Room " + room.getName());
                } else {
                    System.out.println(error.getMessage());
                }
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
    public Object[][] getMap(){
        return map;
    }
}
