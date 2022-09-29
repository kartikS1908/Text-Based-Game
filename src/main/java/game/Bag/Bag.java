package game.Bag;

import game.Inventory;

public class Bag {

    public Bag(){

    }
    Bag_List Bag_List =new Bag_List(5);
    public void enterBag(){
        boolean isFlag =true;
        while (isFlag){
            System.out.println("--------Bag--------");
            System.out.println("1 Add item");
            System.out.println("2 delete item");
            System.out.println("3 List of item");
            System.out.println("4 exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu){
                case '1':
                    addInventory();
                    break;
                case '2':
                    deleteInventory();
                    break;
                case '3':
                    getAll();
                    break;
                case '4':
                    System.out.println("(Y/N)");
                   char isExit = CMUtility.readConfirmSelection();
                  if(isExit =='Y'){
                      isFlag=false;

                  }
                  break;
            }

        }

    }

    private void addInventory(){
        System.out.println("-------------add item--------------");
        System.out.println("ID:");
        String id = CMUtility.readString(10);
        System.out.println("Value:");
        int value = CMUtility.readInt();
        Inventory inventory = new Inventory(id,value);
        boolean isSuccess = Bag_List.addInventory(inventory);
        if(isSuccess) {
            System.out.println("--------successfully added----------");
        }
        else {
            System.out.println("------- fail to add--------------");
        }
    }

    private void deleteInventory(){
        System.out.println(" -------Delete item--------");
        int number;
        for(;;) {
            System.out.println("--------Please choose item number-------");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            Inventory inventory =Bag_List.getInventory(number-1);
            if(inventory==null){
                System.out.println("NO ITEM TO DELETE");
            }else {
                break;
            }

        }
        System.out.println("Y/N");
        char isDelete =CMUtility.readConfirmSelection();
        if(isDelete=='Y'){
            Bag_List.deleteInventory(number-1);
            System.out.println("-------successfully---------\n" );
        }else {
            System.out.println("------Fail--------");
        }
    }

    private void getAll(){
        System.out.println("-----------List of item------------");
        int total = Bag_List.getTotal();
        if(total==0){
            System.out.println("Nothing in Bag!");

        }
        else {
            System.out.println("name\tvalue");
            Inventory[] custs =Bag_List.getAll();
            for(int i =0;i<custs.length;i++){
                Inventory cust = custs[i];
                System.out.println((i+1)+"\t"+cust.getName()+"\t"+cust.getAmount()+"\t");
            }
        }
        System.out.println("-----------Complete----------------\n");
    }


    public static void main(String[] args) {
        Bag view =new Bag();
        view.enterBag();


    }

}
