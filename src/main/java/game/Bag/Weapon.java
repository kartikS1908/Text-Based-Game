package game.Bag;

/**
 *
 */
public class Weapon {
    private String name;
    private int ID;
    private int attack;


    /**
     * @author Dehao Liu
     * @param name
     * @param attack
     */
    public  Weapon(String name,int id, int attack) {

        this.name = name;
        this.ID= id;
        this.attack = attack;


    }

    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public int getID() {
        return ID;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public String getName() {
        return name;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public int getAttack() {
        return attack;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: This is a example, param and return goes here.
     */
    @Override
    public String toString()
    {
        if(this.name.equals(null)) return "";
        return "Name of the weapon is :" + name + " Weapon ID is : " + ID + " It does " + attack + " damage.";
    }


}
