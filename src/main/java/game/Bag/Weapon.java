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
     * get method weapon ID
     * @author Dehao Liu
     *
     */
    public int getID() {
        return ID;
    }
    /**
     * setter method weapon ID
     * @author Dehao Liu
     *
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * get method weapon ID
     * @author Dehao Liu
     *
     */
    public String getName() {
        return name;
    }
    /**
     * get method weapon name
     * @author Dehao Liu
     *
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * get attack
     * @author Dehao Liu
     *
     */
    public int getAttack() {
        return attack;
    }
    /**
     * set attack
     * @author Dehao Liu
     *
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }
    /**
     *
     * @author Dehao Liu
     * @paramreturn  weapon information
     */
    @Override
    public String toString()
    {
        if(this.name.equals(null)) return "";
        return "Name of the weapon is :" + name + " Weapon ID is : " + ID + " It does " + attack + " damage.";
    }


}
