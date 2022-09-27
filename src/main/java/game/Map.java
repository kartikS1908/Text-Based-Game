package game;

import constants.Difficulty;

import game.Bag.Weapon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Random;

public class Map {
    private Difficulty difficulty;
    private int numOfRooms;
    private Room currentRoom;
    private Object[][] map;
    private int XMax = 0;
    private int YMax = 0;
    private JSONObject settings;

    private Object currentSpot;


    public Map(Difficulty difficulty, JSONObject settings){
        this.settings = settings;
        this.numOfRooms = Integer.parseInt(settings.get("numRooms").toString());
        int xSize = Integer.parseInt(settings.get("X-size").toString()), ySize = Integer.parseInt(settings.get("Y-size").toString());
        this.XMax = xSize;
        this.YMax = ySize;
        this.map = createMap(this.numOfRooms, xSize, ySize);
    }

    private Object[][] createMap(int numOfRooms, int xSizeOfGrid, int ySizeOfGrid){
        Object[][] map = new Object[xSizeOfGrid][ySizeOfGrid];
        fillMap(map);
        map[0][0] = "**";
        this.currentSpot = map[0][0];
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
//                            id?                                                       *
                            Weapon weapon = new Weapon(weaponArr.get(0).toString(), i+1, Integer.parseInt(weaponArr.get(1).toString()), "");
//                            HP                            *         *
                            Enemy enemy = new Enemy(i+1, enemyArr.get(0).toString(), weapon);

                            room.setEnemy(enemy);
                        }
                    }
                    case 't' -> {
                        if(!(str.substring(9).equals("nil"))){
                               room.setCountOfTreasure(Integer.parseInt(str.substring(9).toString()));
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
                            switch (item) {
                                case "h1" -> room.setInventory(new Inventory("small healing potion", amount));
                                case "s1" -> room.setInventory(new Inventory("small stamina booster", amount));
                                case "h2" -> room.setInventory(new Inventory("big healing portion", amount));
                                case "s2" -> room.setInventory(new Inventory("big stamina booster", amount));
                            }
                        }
                    }
                }
            }

            map[randomX][randomY] = room;
        }
    }

//    private void goNorth(){
//        int x = this.getCurrentX();
//        if ((x-1) < 0){
//            System.out.println("Sorry, no exit");
//        }else{
//            setCurrentX(x-1);
//        }
//    }
//    private void goSouth(){
//        int x = this.getCurrentX();
//        if ((x+1) > this.getMap().length){
//            System.out.println("Sorry, no exit");
//        }else{
//            setCurrentX(x+1);
//        }
//    }
//    private void goWest(){
//        int y = this.getCurrentY();
//        if ((y-1) < 0){
//            System.out.println("Sorry, no exit");
//        }else{
//            setCurrentY(y-1);
//        }
//    }
//    private void goEast(){
//        int y = this.getCurrentY();
//        if ((y+1) > this.getMap()[0].length){
//            System.out.println("Sorry, no exit");
//        }else{
//            setCurrentY(y+1);
//        }
//    }
//
//    public String ProcessCommand(String command){
//        String msg = "";
//        switch (command){
//            case "north"-> goNorth();
//            case "south"-> goSouth();
//            case "west"-> goWest();
//            case "east"-> goEast();
//            default -> msg = command + "not available";
//        }
//        return msg;
//    }

    public Object getCurrentSpot(int x, int y){
        if(x < 0 || x >= this.XMax || y < 0 || y >= this.YMax){
            return "Sorry, no exit!";
        }

        if(this.map[x][y] instanceof Room){
            return this.map[x][y];
        }else{
            return "Nothing here. Please move forward.";
        }
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


    public Object[][] getMap(){
        return this.map;
    }
}
