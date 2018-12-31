/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 22:14
 */

package GamePackage.CreaturesStuff;

import GamePackage.ItemsStuff.Item;
import GamePackage.ItemsStuff.Loot;
import GamePackage.ItemsStuff.Weapon;

import java.util.ArrayList;

public class Hero extends Creature {
    // Field variables
    private HeroClass heroClass;
    private ArrayList<Item> inventory;
    private Weapon weapon;
    private int score;
    int agility;
    // TODO add keys

    // Constructors
    public Hero(String name, int xIndex, int yIndex, int maxHealth, int damage, HeroClass heroClass, int agility) {
        super(name, xIndex, yIndex, maxHealth, damage);
        this.heroClass = heroClass;
        this.agility = agility;
        inventory = new ArrayList<>();
        score = 0;
    }


    // Methods

    public int getDamage() {
        int damage = super.getDamage();
        if (this.weapon != null) {
            damage += weapon.getDamage();
        }
        return damage;
    }

    public void printCurrentStats() {
        System.out.println("Current score: " + score);
        if (weapon != null) {
            System.out.println("You are wielding the " + weapon.getName());
        }
        if (inventory.size() == 0) {
            System.out.println("Your inventory is empty!");
        } else {
            System.out.println("You have " + inventory.size() + " items in your inventory.");
        }
        this.printCurrentHealth();
        System.out.println();
    }

    public void printCurrentHealth() {
        System.out.println("Current HP/Max HP - " + this.getCurrentHealth() + "/" + this.getMaxHealth());
    }

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

    public void pickupLoot(Loot loot) {
        this.score += loot.getWorthInCoins();
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
