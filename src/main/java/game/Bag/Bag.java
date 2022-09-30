package game.Bag;

import game.Inventory;

import java.util.ArrayList;

public class Bag {
    private ArrayList<Inventory> inventories;
    private int size;
    /**
     * Bag Constructor to initiate a Bag object
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @param size the max size of a bag
     */
    public Bag(int size) {
        this.size = size;
        this.inventories = new ArrayList<Inventory>();
    }
    /**
     * Put an inventory into the bag
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @param inventory an inventory to be added
     */
    public void addInventory(Inventory inventory){
        if (this.inventories.size() >= this.size){
            System.out.println("You bag is full!");
            return;
        }
        this.inventories.add(inventory);
    }
    /**
     * Drop an inventory from the bag
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @param index the index of the inventory to be dropped
     */
    public void dropInventory(int index){
        if(index < 1 || index > this.size){
            System.out.println("Wrong choice, please choose again");
        }
        this.inventories.remove(index - 1);
    }
    /**
     * Return all the inventories in a bag
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @return ArrayList<Inventory> all the inventories in the bag
     */
    public ArrayList<Inventory> getInventories() {
        return inventories;
    }
    /**
     * Check if the index of an inventory to be operated is valid or not
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @param index the index of an inventory to be operated
     */
    public boolean checkIsIndexValid (int index){
        if(index < 1 || index > inventories.size()){
            return false;
        }
        return true;
    }
    /**
     * Override toString method so that it can provide meaningful information of inventories in a bag
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * @return String information of inventories in a bag
     */
    @Override
    public String toString() {
        if(this.inventories.size() == 0){
            return "Nothing in the bag!";
        }
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < this.inventories.size(); i++){
            output.append(i + 1).append(". ").append(this.inventories.get(i).getDes()).append("\n");
        }
        return output.toString();
    }
}
