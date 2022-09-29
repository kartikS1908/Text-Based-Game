package game.Bag;

import game.Inventory;

public class Bag_List {
    private Inventory[] inventory;
    private int total ;

    /**
     * Array constructor to use to initialize weapon
     * @param total:
     */

    public Bag_List(int total){

        this.inventory =new Inventory[total];

    }

    /**
     * @Description:
     * @author Dehao liu
     * @date:2022
     * @param inv
     * @return ture
     */
    public boolean addInventory(Inventory inv){
        if(total>=inventory.length){
            return false;
        }
        inventory[total]=inv;
        total++;
        return true;
    }

//    /**
//     *
//     * @param index
//     *  @param cust
//     * @return
//     */
//    public boolean replaceWeapon(Weapon cust ,int index){
//
//        if(index<0||index>total){
//            return false;
//        }
//        weapons[index]=cust;
//        return true;
//    }

    /**
     *
     * @param index
     * @return
     */
    public boolean deleteInventory(int index){
        if(index<0||index>=total){
            return false;
        }
        for(int i =index;i<total-1;i++){
            inventory[i]=inventory[i+1];
        }
        inventory[total-1]=null;
        total--;
        return true;
    }
    /**
     *
     */
    public Inventory[] getAll(){
       Inventory[] custs =new Inventory[total];
       for(int i = 0;i<total;i++){
           custs[i] =inventory[i];

       }
       return custs;
    }

    public Inventory getInventory(int index){

        if(index<0||index>=total){
            return null;
        }
        return inventory[index];
    }

public int getTotal(){
        return total;
}





}

