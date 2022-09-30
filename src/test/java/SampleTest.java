import org.junit.Before;
import org.junit.Test;

import game.ConfigReader;
import game.Enemy;
import game.Inventory;
import game.Main;
import game.Room;
import game.Bag.BagList;
import game.Bag.Weapon;
import game.Character;
import game.Map;
import game.Movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;

public class SampleTest {
    ConfigReader configReader = new ConfigReader();
    JSONObject jsonObject = configReader.read("./src/Configs/Engine.json");
    JSONObject gameObj = (JSONObject) jsonObject.get("GAME");

    @Test
    public void testSimple2() {
        assertEquals("This is a sample test case it does not do anything",1 ,1);
    }

    @Test
    public void testMap1() {
        Character player = new Character("abc", null, 0, null);
        // TODO: Harry
        // room overlapping
    }

    @Test
    public void testRoom1() {
        // Test if getCurrentPosition can return the Room or null after a move
        gameObj = (JSONObject) gameObj.get("EASY");
        Character player = new Character("abc", null, 0, null);
        Map map = new Map(gameObj, player);
    
        player.setCurrentPosition(2, 0); // There is a room in map (2,0)
        assertTrue(map.getCurrentPosition() != null);

        player.setCurrentPosition(3, 0); // There is no room in map (3,0)
        assertTrue(map.getCurrentPosition() == null);
    }

    @Test
    public void testMovement1() {
        // Test if the player can move out of the map
        gameObj = (JSONObject) gameObj.get("EASY");
        Character player = new Character("abc", null, 0, null);
        Map map = new Map(gameObj, player);

        player.setCurrentPosition(0, 0);
        Movement movement = new Movement(player, map);

        movement.move("W");
        assertEquals(0, player.getCurrentY());

        movement.move("A");
        assertEquals(0, player.getCurrentX());
    }

    @Test
    public void testMovement2() {
        gameObj = (JSONObject) gameObj.get("EASY");
        Character player = new Character("abc", null, 0, null);
        Map map = new Map(gameObj, player);
        // TODO: Harry
        // Stamina + -
    }

    @Test
    public void testInventory1() {
        Character player = new Character("abc", null, 0, null);
        // TODO: Jiayuan
        // Size logic
    }

    @Test
    public void testCharacter1() {
        Character player = new Character("abc", null, 0, null);
        // TODO: Jiayuan
    }

    @Test
    public void testBag1() {
        Character player = new Character("abc", null, 0, null);
        // TODO: Dehao
        // Size logic
    }

    @Test
    public void testFight1() {
        // Test if attack function works when the attack in Correct value range
        Weapon test_weapon1 = new Weapon("test1 weapon", 0, 10);
        Weapon test_weapon2 = new Weapon("test2 weapon", 1, 30);
        Character player = new Character("abc", test_weapon1, 0, null);
        Enemy enemy = new Enemy(0, "cba", test_weapon2, null);
        enemy.attack(player);
        assertEquals(70, player.getHP());

        player.attack(enemy);
        assertEquals(90, enemy.getHP());
    }

    @Test
    public void testFight2() {
        // Test if attack function works when the attack in Incorrect value range: Greater than player and enemy Max HP
        // Design: When player or enemy HP is lower than 100, it will still be 0 HP
        Weapon test_weapon1 = new Weapon("test1 weapon", 0, 500);
        Weapon test_weapon2 = new Weapon("test2 weapon", 1, 500);
        Character player = new Character("abc", test_weapon1, 0, null);
        Enemy enemy = new Enemy(0, "cba", test_weapon2, null);
        enemy.attack(player);
        assertEquals(0, player.getHP());

        player.attack(enemy);
        assertEquals(0, enemy.getHP());
    }

    @Test
    public void testFight3() {
        // Test if attack function works when the attack 0
        // Design: Player and enemy attack only depend on weapon ATK
        Weapon test_weapon1 = new Weapon("test1 weapon", 0, 0);
        Weapon test_weapon2 = new Weapon("test2 weapon", 1, 0);
        Character player = new Character("abc", test_weapon1, 0, null);
        Enemy enemy = new Enemy(0, "cba", test_weapon2, null);
        enemy.attack(player);
        assertEquals(100, player.getHP());

        player.attack(enemy);
        assertEquals(100, enemy.getHP());
    }

    @Test
    public void testPotion1() {
        // Test if useInventory function works when the potion amount in Correct value range
        Character player = new Character("abc", null, 0, null);

        player.setHP(10);
        player.setStamina(1);

        BagList bag = new BagList(5);
        Inventory inv1 = new Inventory("h1", 10);
        Inventory inv2 = new Inventory("h2", 20);
        Inventory inv3 = new Inventory("s1", 10);
        Inventory inv4 = new Inventory("s2", 20);
        bag.addInventory(inv1);
        bag.addInventory(inv2);
        bag.addInventory(inv3);
        bag.addInventory(inv4);

        player.setBag(bag);

        // HP = 10 + 10(h1)
        player.useInventory(1);
        assertEquals(20, player.getHP());

        // HP = 20 + 20(h2)
        player.useInventory(1);
        assertEquals(40, player.getHP());

        // stamina = 1 + 10(s1)
        player.useInventory(1);
        assertEquals(11, player.getStamina());

        // stamina = 11 + 20(s1)
        player.useInventory(1);
        assertEquals(31, player.getStamina());
    }

    @ Test
    public void testPotion2() {
        // Test if useInventory function works when the potion amount is zero
        Character player = new Character("abc", null, 0, null);

        player.setHP(10);
        player.setStamina(1);

        BagList bag = new BagList(5);
        Inventory inv1 = new Inventory("h1", 0);
        Inventory inv2 = new Inventory("h2", 0);
        Inventory inv3 = new Inventory("s1", 0);
        Inventory inv4 = new Inventory("s2", 0);
        bag.addInventory(inv1);
        bag.addInventory(inv2);
        bag.addInventory(inv3);
        bag.addInventory(inv4);

        player.setBag(bag);

        // HP = 10 + 0(h1)
        player.useInventory(1);
        assertEquals(10, player.getHP());

        // HP = 10 + 0(h2)
        player.useInventory(1);
        assertEquals(10, player.getHP());

        // stamina = 1 + 0(s1)
        player.useInventory(1);
        assertEquals(1, player.getStamina());

        // stamina = 1 + 0(s1)
        player.useInventory(1);
        assertEquals(1, player.getStamina());
    }

    @ Test
    public void testPotion3() {
        // Test if useInventory function works when the potion amount is out of bound(MaxHP)
        Character player = new Character("abc", null, 0, null);

        player.setHP(10);
        player.setStamina(1);

        BagList bag = new BagList(5);
        Inventory inv1 = new Inventory("h1", 500);
        Inventory inv2 = new Inventory("h2", 500);
        bag.addInventory(inv1);
        bag.addInventory(inv2);

        player.setBag(bag);

        // HP = 10 + 500(h1) = 510 => 100
        player.useInventory(1);
        assertEquals(100, player.getHP());

        player.setHP(10);
        // HP = 10 + 500(h2) = 510 => 100
        player.useInventory(1);
        assertEquals(100, player.getHP());
    }
}