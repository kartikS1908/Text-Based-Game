package game;

import constants.Difficulty;
import constants.Direction;

import java.util.ArrayList;

public class Map {
    private Difficulty difficulty;
    private int numOfRooms;
    private Room currentRoom;
    private ArrayList<Room> map;

    public Map(Difficulty difficulty){
       if(difficulty == Difficulty.EASY) {
           this.numOfRooms = 4;
           this.map = getEasyMap();
       }else if(difficulty == Difficulty.MEDIUM) {
           this.numOfRooms = 6;
           this.map = getEasyMap();
       }else if(difficulty == Difficulty.HARD) {
           this.numOfRooms = 8;
           this.map = getEasyMap();
       }
       this.currentRoom = getEasyMap().get(0);
    }

    public ArrayList<Room> getEasyMap(){
        ArrayList<Room> rooms = new ArrayList<Room>();

        rooms.add(new Room("Room1", "Room 1", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        rooms.add(new Room("Room2", "Room 2", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        rooms.add(new Room("Room3", "Room 3", 0, Direction.NOEXIT, Direction.NOEXIT, 4));
        rooms.add(new Room("Room4", "Room 4", Direction.NOEXIT, Direction.NOEXIT,2,  Direction.NOEXIT));

        return rooms;
    }

    public ArrayList<Room> getMap() {
        return map;
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

    private void goNorth(){
        moveTo(Direction.NORTH);
    }

    private void goSouth(){
        moveTo(Direction.SOUTH);
    }

    private void goWest(){
        moveTo(Direction.WEST);
    }

    private void goEast(){
        moveTo(Direction.EAST);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private int moveTo(Direction direction){
        int exit;
        Room room = this.getCurrentRoom();

        switch (direction){
            case NORTH -> exit = room.getNorth();
            case SOUTH -> exit = room.getSouth();
            case WEST -> exit = room.getWest();
            case EAST -> exit = room.getEast();
            default -> exit = Direction.NOEXIT;
        }

        if(exit != Direction.NOEXIT){
            setCurrentRoom(map.get(exit));
        }
        return exit;
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
}
