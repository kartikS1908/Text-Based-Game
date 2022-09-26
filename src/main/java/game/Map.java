package game;

import constants.Difficulty;
import constants.Direction;
import constants.InvType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



public class Map {
    private Difficulty difficulty;
    private int numOfRooms;
    private Room currentRoom;
    private Object[][] map;
    private int currentX = 0;
    private int currentY = 0;
    private JSONObject settings;


    public Map(Difficulty difficulty, JSONObject settings){
        this.settings = settings;
        this.numOfRooms = Integer.parseInt(settings.get("numRooms").toString());
        int xSize = Integer.parseInt(settings.get("X-size").toString()), ySize = Integer.parseInt(settings.get("Y-size").toString());
        this.map = createMap(this.numOfRooms, xSize, ySize);
    }

    private Object[][] createMap(int numOfRooms, int xSizeOfGrid, int ySizeOfGrid){
        Object[][] map = new Object[xSizeOfGrid][ySizeOfGrid];
        fillMap(map);
        map[0][0] = "**";
        for(Object[] row : map){
            System.out.println(Arrays.toString(row));
        }
        return map;
    }

    private void fillMap(Object[][] map){
//      First fill the map by '*'
        for(int i = 0; i < map.length; i++){
            Arrays.fill(map[i], "--");
        }
//      Start putting the rooms into the grid randomly
        Random random = new Random();

        for(int i = 0; i < numOfRooms; i++){
//          Find the position of a room
            int randomX = random.nextInt(map.length);
            int randomY = random.nextInt(map[0].length);
//          If the current spot is a room, randomise the indices
            while(map[randomX][randomY] instanceof Room){
               randomX = random.nextInt(map.length);
               randomY = random.nextInt(map[0].length);
            }

            JSONArray array = (JSONArray) this.settings.get("room"+ (i + 1));
            Room room = new Room();
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
//                            Weapon weapon = new Weapon()
                            System.out.println("Enemy Name is " + enemyArr.get(0) + ", They say " + enemyArr.get(2) + ", They posses the " + (String) weaponArr.get(0) + ", It does " + weaponArr.get(1) + " damage.");

                        }
                    }
                }
            }

            int hasWeapon = random.nextInt(2);
            int invTypeLen = InvType.values().length;
            int invType = random.nextInt(invTypeLen);

            Weapon weapon = hasWeapon != 0 ? new Weapon() : null;
            Inventory inventory = weapon == null ? new Inventory(InvType.values()[invType % invTypeLen]) : null;
            Enemy enemy = new Enemy();
            map[randomX][randomY] = new Room("Room"+(i+1), "Room " + (i + 1), weapon, inventory, enemy);

        }
    }

    private void goNorth(){
        int x = this.getCurrentX();
        if ((x-1) < 0){
            System.out.println("Sorry, no exit");
        }else{
            setCurrentX(x-1);
        }
    }
    private void goSouth(){
        int x = this.getCurrentX();
        if ((x+1) > this.getMap().length){
            System.out.println("Sorry, no exit");
        }else{
            setCurrentX(x+1);
        }
    }
    private void goWest(){
        int y = this.getCurrentY();
        if ((y-1) < 0){
            System.out.println("Sorry, no exit");
        }else{
            setCurrentY(y-1);
        }
    }
    private void goEast(){
        int y = this.getCurrentY();
        if ((y+1) > this.getMap()[0].length){
            System.out.println("Sorry, no exit");
        }else{
            setCurrentY(y+1);
        }
    }

    public String ProcessCommand(String command){
        String msg = "";
        switch (command){
            case "north"-> goNorth();
            case "south"-> goSouth();
            case "west"-> goWest();
            case "east"-> goEast();
            default -> msg = command + "not available";
        }
        return msg;
    }





//    void updateOutput(int roomNumber){
//        String s;
//        if(roomNumber == Direction.NOEXIT){
//            s = "Sorry, no exit!";
//        }else{
//            Room room = getCurrentRoom();
//            s = "You are in " + room.getName() + ". " + room.getDescription();
//        }
//        System.out.println(s);
//    }


    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public Object[][] getMap(){
        return this.map;
    }
}
