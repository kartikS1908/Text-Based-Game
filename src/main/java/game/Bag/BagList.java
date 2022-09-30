package game.Bag;

import game.Inventory;

import java.util.ArrayList;

public class BagList {
    private ArrayList<Inventory> inventories;
    private int size;

    public BagList(int size) {
        this.size = size;
        this.inventories = new ArrayList<Inventory>();
    }

    public void addInventory(Inventory inventory){
        if (this.inventories.size() >= this.size){
            System.out.println("You bag is full!");
            return;
        }
        this.inventories.add(inventory);
    }

    public void dropInventory(int index){
        if(index < 1 || index > this.size){
            System.out.println("Wrong choice, please choose again");
        }
        this.inventories.remove(index - 1);
    }

    public ArrayList<String> getIDs(){
        ArrayList<String> itemIDs = new ArrayList<>();
        for(Inventory elem : inventories)
        {
            itemIDs.add(elem.getInvID());
        }
        return itemIDs;
    }
    public ArrayList<Inventory> getInventories() {
        return inventories;
    }

    public boolean checkIsIndexValid (int index){
        if(index < 1 || index > inventories.size()){
            return false;
        }
        return true;
    }

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
