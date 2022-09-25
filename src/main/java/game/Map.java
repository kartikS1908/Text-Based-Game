package game;

import constants.Difficulty;
import constants.Direction;
import constants.InvType;

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


    public Map(Difficulty difficulty){
       if(difficulty == Difficulty.EASY) {
           this.numOfRooms = 4;
           this.map = createMap(4, 6);
       }else if(difficulty == Difficulty.MEDIUM) {
           this.numOfRooms = 6;
           this.map = createMap(6, 6);
       }else if(difficulty == Difficulty.HARD) {
           this.numOfRooms = 8;
           this.map = createMap(8, 6);
       }
    }

    private Object[][] createMap(int numOfRooms, int sizeOfGrid){
        Object[][] map = new Object[sizeOfGrid][sizeOfGrid];
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
            int randomX = random.nextInt(map.length);
            int randomY = random.nextInt(map[0].length);
//          If the current spot is a room, randomise the indices
            while(map[randomX][randomY] instanceof Room){
               randomX = random.nextInt(map.length);
               randomY = random.nextInt(map[0].length);
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
