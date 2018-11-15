package GamePackage;

import java.util.ArrayList;

public class Monster extends Creature {
    // Field variables
    ArrayList<Loot> lootArray = new ArrayList<Loot>();

    // Constructors
    public Monster(String name, int[][] roomCurrentlyInside, int maxHealth, int damage) {
        super(name, roomCurrentlyInside, maxHealth, damage);
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
