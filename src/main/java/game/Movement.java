package game;

public class Movement {
    private Map map;
    private Character player;

    public Movement(Character player, Map map){
        this.player = player;
        this.map = map;
    }

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
                }
            }
            case "w" -> {
                if (currentY - 1 < 0){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentY--;
                }
            }
            case "a" -> {
                if (currentX - 1 < 0){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentX--;
                }
            }
            case "d" -> {
                if (currentX + 1 >= map.getXMax()){
                    System.out.println("Warning! You will cross the boundary");
                }else{
                    currentX++;
                }
            }

        }

        this.player.setStamina(currentStamina - 1);
        this.player.setCurrentPosition(currentX, currentY);
        System.out.println("Current Stamina is " + currentStamina + ", Current HP is " + this.player.getHP());
    }
}
