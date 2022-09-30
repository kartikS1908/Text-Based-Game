package game.Bag;

import game.Inventory;

import java.util.ArrayList;

public class BagList {
    private ArrayList<Inventory> inventories;
    private int size;
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public BagList(int size) {
        this.size = size;
        this.inventories = new ArrayList<Inventory>();
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public void addInventory(Inventory inventory){
        if (this.inventories.size() >= this.size){
            System.out.println("You bag is full!");
            return;
        }
        this.inventories.add(inventory);
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public void dropInventory(int index){
        if(index < 1 || index > this.size){
            System.out.println("Wrong choice, please choose again");
        }
        this.inventories.remove(index - 1);
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public ArrayList<Inventory> getInventories() {
        return inventories;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
     */
    public boolean checkIsIndexValid (int index){
        if(index < 1 || index > inventories.size()){
            return false;
        }
        return true;
    }
    /**
     * TODO: what this function do.
     * @author Harry Li
     * @author Kartik Sharma
     * @author Jiayuan Zhu
     * @author Dehao Liu
     * @author Xilai Wang
     * TODO: This is a example, param and return goes here.
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
