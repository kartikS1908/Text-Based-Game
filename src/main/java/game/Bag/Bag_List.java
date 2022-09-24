package game.Bag;

public class Bag_List {
    private Weapon[] weapons;
    private int total ;

    /**
     * Array constructor to use to initialize weapon
     * @param totalWeapon:
     */
    public Bag_List(int totalWeapon){
        weapons =new Weapon[totalWeapon];


    }

    /**
     * @Description:
     * @author Dehao liu
     * @date:2022
     * @param weapon
     * @return ture
     */
    public boolean addWeapon(Weapon weapon){
        if(total>=weapons.length){
            return false;
        }
        weapons[total]=weapon;
        total++;
        return true;
    }

    /**
     *
     * @param index
     *  @param cust
     * @return
     */
    public boolean replaceWeapon(Weapon cust ,int index){

        if(index<0||index>total){
            return false;
        }
        weapons[index]=cust;
        return true;
    }

    /**
     *
     * @param index
     * @return
     */
    public boolean deleteWeapon(int index){
        if(index<0||index>=total){
            return false;
        }
        for(int i =index;i<total-1;i++){
            weapons[i]=weapons[i+1];

        }
        weapons[total-1]=null;
        total--;
        return true;

    }
    /**
     *
     */
    public Weapon[] getAllWeapons(){
       Weapon[] custs =new Weapon[total];
       for(int i = 0;i<total;i++){
           custs[i] =weapons[i];

       }
       return custs;
    }
    public Weapon getWeapons(int index){

        if(index<0||index>=total){
            return null;
        }
        return weapons[index];
    }
public int getTotal(){
        return total;
}





}

