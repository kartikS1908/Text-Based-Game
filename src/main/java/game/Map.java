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

    public Map(JSONObject settings, Character player){
        this.settings = settings;
        this.numOfRooms = Integer.parseInt(settings.get("numRooms").toString());
        int xSize = Integer.parseInt(settings.get("X-size").toString());
        int ySize = Integer.parseInt(settings.get("Y-size").toString());
        this.XMax = xSize;
        this.YMax = ySize;
        this.player = player;
        this.playerX = player.getCurrentY(); // !!!!
        this.playerY = player.getCurrentX(); // !!!!
        this.map = createMap(xSize, ySize);
        printMap();
    }

    private Object[][] createMap(int xSizeOfGrid, int ySizeOfGrid){
        Object[][] map = new Object[xSizeOfGrid][ySizeOfGrid];
        fillMap(map);
        int x = this.playerX;
        int y = this.playerY;
        map[x][y] = "￣▽￣";
        this.prevPosition = new int[]{x, y};
        return map;
    }
    public void printMap(){
        updateMap();
        for(Object[] row : this.map){
            System.out.println(Arrays.toString(row));
        }
    }

    private void updateMap(){
        updatePlayerPosition();
        this.map[this.prevPosition[0]][this.prevPosition[1]] = "---";
        this.map[this.playerX][this.playerY] = "￣▽￣";
        this.prevPosition = new int[]{this.playerX, this.playerY};
    }

    public int getXMax() {
        return XMax;
    }

    public int getYMax() {
        return YMax;
    }

    private void updatePlayerPosition(){
        this.playerX = player.getCurrentY();
        this.playerY = player.getCurrentX();
    }

    public Object getCurrentPosition(){
        updatePlayerPosition();
        Object currentPosition = this.map[this.playerX][this.playerY];
        if(currentPosition instanceof Room){
            return currentPosition;
        }
        return null;
    }

    private void fillMap(Object[][] map){
//      First fill the map by '*'
        for(int i = 0; i < map.length; i++){
            Arrays.fill(map[i], "---");
        }

        for(int i = 0; i < this.numOfRooms; i++){
            JSONArray array = (JSONArray) this.settings.get("room"+ (i + 1));
            Room room = new Room();
            int x = 0, y = 0;
            for(Object obj : array){
                String str = (String) obj;

                switch (str.charAt(0)){
                    case 'r' -> room.setName(str.substring(9));
                    case 'e' -> {
                        if(str.substring(6).equals("nil")) {
                            room.setEnemy(null);
                        } else {
                            JSONArray enemyArr = (JSONArray) this.settings.get(str.substring(6));
                            JSONArray weaponArr = (JSONArray) this.settings.get(enemyArr.get(1));

                            Weapon weapon = new Weapon(weaponArr.get(0).toString(), i+1, Integer.parseInt(weaponArr.get(1).toString()), "");
                            Enemy enemy = new Enemy(i+1, enemyArr.get(0).toString(), weapon);

                            room.setEnemy(enemy);
                        }
                    }
                    case 't' -> {
                        if(!(str.substring(9).equals("nil"))){
                               room.setCountOfTreasure(Integer.parseInt(str.substring(9)));
                        }
                    }
                    case 'w' -> {
                        if(!(str.substring(7).equals("nil"))){
                            JSONArray weaponArr = (JSONArray) this.settings.get(str.substring(7));
                            Weapon weapon = new Weapon(weaponArr.get(0).toString(), i+1, Integer.parseInt(weaponArr.get(1).toString()), "");
                            room.setWeapon(weapon);
                        }
                    }
                    case 'i' -> {
                        if(!(str.substring(7).equals("nil"))) {
                            String item = str.substring(10);
                            int amount = Integer.parseInt(this.settings.get(item).toString());
                            room.setInventory(new Inventory(item, amount));
                        }
                    }
                    case 'X' -> {
                        x = Integer.parseInt(str.substring(2));
                        try{
                            if(x >= this.XMax){
                                throw new HandledException("****** X coordinate exceeds grid/map limit ******");
                            }
                        }catch (HandledException e){
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }

                    }
                    case 'Y' -> {
                        y = Integer.parseInt(str.substring(2));
                        try {
                            if(y >= this.YMax){
                                throw new HandledException("****** Y coordinate exceeds grid/map limit ******");
                            }
                        }catch (HandledException e){
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }

            map[x][y] = room;
        }


    }


    public Object[][] getMap(){
        return this.map;
    }
}
