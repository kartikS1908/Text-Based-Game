package game.Bag;

public class Bag {
    public Bag(){
        Weapon weapon =new Weapon("Gun",10,20,"useful");
        Bag_List.addWeapon(weapon);
    }
    game.Bag.Bag_List Bag_List =new Bag_List(10);
    public void enterBag(){
        boolean isFlag =true;
        while (isFlag){
            System.out.println("--------Bag--------");
            System.out.println("1 Add item");
            System.out.println("2 replace item");
            System.out.println("3 delete item");
            System.out.println("4 List of item");
            System.out.println("5 exit");
            char menu = CMUtility.readMenuSelection();
            switch (menu){
                case '1':
                    addWeapon();
                    break;
                case '2':
                    replaceWeapon();
                    break;
                case '3':
                    deleteWeapon();
                    break;
                case '4':
                    getAllWeapon();
                    break;
                case '5':
                    System.out.println("(Y/N)");
                   char isExit = CMUtility.readConfirmSelection();
                  if(isExit =='Y'){
                      isFlag=false;

                  }
                  break;
            }

        }

    }
    private void addWeapon(){
        System.out.println("-------------add item--------------");
        System.out.println("name:");
       String name = CMUtility.readString(10);
        System.out.println("ID:");
        int ID = CMUtility.readInt();
        System.out.println("attack:");
        int attack = CMUtility.readInt();
        System.out.println("introduction");
        String introduction=CMUtility.readString(200);
        Weapon weapon =new Weapon(name,ID,attack,introduction);
        boolean isSuccess = Bag_List.addWeapon(weapon);
        if(isSuccess) {

            System.out.println("--------successfully added----------");
        }
        else {
            System.out.println("------- fail to add--------------");
        }


    }
    private void replaceWeapon(){
        System.out.println("replace item");

    }
    private void deleteWeapon(){
        System.out.println(" -------Delete item--------");
        int number;
        for(;;) {
            System.out.println("--------Please choose item number-------");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            Weapon weapon =Bag_List.getWeapons(number-1);
            if(weapon==null){
                System.out.println("Can't fond!");
            }else {
                break;
            }

        }
        System.out.println("Y/N");
        char isDelete =CMUtility.readConfirmSelection();
        if(isDelete=='Y'){
            Bag_List.deleteWeapon(number-1);
            System.out.println("-------successfully---------\n" );
        }else {
            System.out.println("------Fail--------");
        }
    }
    private void getAllWeapon(){
        System.out.println("-----------List of item------------");
        int total = Bag_List.getTotal();
        if(total==0){
            System.out.println("Nothing in Bag!");

        }
        else {
            System.out.println("name\tID\tattack\tintroduction");
            Weapon[] custs =Bag_List.getAllWeapons();
            for(int i =0;i<custs.length;i++){
                Weapon cust = custs[i];
                System.out.println((i+1)+"\t"+cust.getName()+"\t"+cust.getID()+"\t"+cust.getAttack()+"\t"+cust.getIntroduction());
            }
        }
        System.out.println("-----------Complete----------------\n");
    }


    public static void main(String[] args) {
        Bag view =new Bag();
        view.enterBag();




    }

}
