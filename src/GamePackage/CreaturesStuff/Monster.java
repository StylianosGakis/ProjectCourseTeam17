package GamePackage.CreaturesStuff;

import GamePackage.ItemsStuff.Loot;
import GamePackage.MapStuff.Map;
import GamePackage.MapStuff.Room;

import java.util.ArrayList;

public class Monster extends Creature {
    // Field variables
    private ArrayList<Loot> lootArray;

    // Constructors
    public Monster(String name, int xIndex, int yIndex, int maxHealth, int damage, ArrayList<Loot> lootArray) {
        super(name, xIndex, yIndex, maxHealth, damage);
        if (lootArray == null){
            this.lootArray = new ArrayList<Loot>();
        } else {
            this.lootArray = lootArray;
        }
    }


    // Methods

    // Getters and setters
    public ArrayList<Loot> getLootArray() {
        return lootArray;
    }
    public void setLootArray(ArrayList<Loot> lootArray) {
        this.lootArray = lootArray;
    }
}
