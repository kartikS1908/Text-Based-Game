package game;

public class Movement {
    private Map map;
    private Character player;

    /**
     * Movement constructor to initiate a Movement object for moving a player within the map
     * @author Harry Li
     * @author Xilai Wang
     * @param player the current player
     * @param map the current map
     */
    public Movement(Character player, Map map){
        this.player = player;
        this.map = map;
    }
    /**
     * Move the player according to specified direction
     * @author Harry Li
     * @author Xilai Wang
     * @param direction the direction to move the player
     */
    public void move(String direction){
        int currentX = this.player.getCurrentX();
        int currentY = this.player.getCurrentY();
        int currentStamina = this.player.getStamina();
        switch (direction.toLowerCase()) {
            case "s" -> {
                if (currentY + 1 >= map.getYMax()){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentY++;
                    this.player.setStamina(currentStamina - 1);
                    System.out.println("The current stamina is: " + this.player.stamina);
                }
            }
            case "w" -> {
                if (currentY - 1 < 0){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentY--;
                    this.player.setStamina(currentStamina - 1);
                    System.out.println("The current stamina is: " + this.player.stamina);
                }
            }
            case "a" -> {
                if (currentX - 1 < 0){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentX--;
                    this.player.setStamina(currentStamina - 1);
                    System.out.println("The current stamina is: " + this.player.stamina);
                }
            }
            case "d" -> {
                if (currentX + 1 >= map.getXMax()){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentX++;
                    this.player.setStamina(currentStamina - 1);
                    System.out.println("The current stamina is: " + this.player.stamina);
                }
            }

        }
        this.player.setCurrentPosition(currentX, currentY);
    }
}
