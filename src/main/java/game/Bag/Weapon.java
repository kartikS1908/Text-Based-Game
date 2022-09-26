package game.Bag;

/**
 *
 */
public class Weapon {
    private String name;
    private int ID;
    private int attack;
    private String introduction;

    /**
     *
     * @param name
     * @param attack
     * @param introduction
     */
    public  Weapon(String name,int id, int attack, String introduction) {

        this.name = name;
        this.ID= ID;
        this.attack = attack;
        this.introduction = introduction;

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
