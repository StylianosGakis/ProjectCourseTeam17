/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 22:14
 */

package GamePackage.CreaturesStuff;

import GamePackage.ItemsStuff.Item;
import GamePackage.ItemsStuff.Loot;

import java.util.ArrayList;

public class Hero extends Creature {
    // Field variables
    private HeroClass heroClass;
    private ArrayList<Item> inventory;
    private int score;
    // TODO add keys

    // Constructors
    public Hero(String name, int xIndex, int yIndex, int maxHealth, int damage, HeroClass heroClass) {
        super(name, xIndex, yIndex, maxHealth, damage);
        this.heroClass = heroClass;
        inventory = new ArrayList<>();
        score = 0;
    }


    // Methods
    // TODO add method for using one of the items, possibly different methods for items/food or even one will see.

    // Getters and setters
    public HeroClass getHeroClass() {
        return heroClass;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public int getScore() {
        return score;
    }

    public void pickupLoot(Loot loot){
        this.score += loot.getWorthInCoins();
    }
}
