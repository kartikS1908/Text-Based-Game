package game.Bag;

/**
 *
 */
public class Weapon {
    private String name;
    private int ID;
    private int attack;


    /**
     *
     * @param name
     * @param attack
     */
    public  Weapon(String name,int id, int attack) {

        this.name = name;
        this.ID= id;
        this.attack = attack;


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

    @Override
    public String toString()
    {
        if(this.name.equals(null)) return "";
        return "Name of the weapon is :" + name + " Weapon ID is : " + ID + " It does " + attack + " damage.";
    }


}
