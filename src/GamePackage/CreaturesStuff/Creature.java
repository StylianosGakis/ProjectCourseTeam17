/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:58
 */

package GamePackage.CreaturesStuff;

import GamePackage.MapStuff.Room;

public abstract class Creature {
    // Field variables
    private String name;
    private Room roomCurrentlyInside;
    private int maxHealth;
    private int currentHealth;
    private int damage;

    // Constructors
    public Creature(String name, Room roomCurrentlyInside, int maxHealth, int damage) {
        this.name = name;
        this.roomCurrentlyInside = roomCurrentlyInside;
        this.maxHealth = maxHealth;
        this.damage = damage;

        this.currentHealth = maxHealth; // Current health always starts as full
    }

    // Methods

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Room getRoomCurrentlyInside() {
        return roomCurrentlyInside;
    }
    public void setRoomCurrentlyInside(Room roomCurrentlyInside) {
        this.roomCurrentlyInside = roomCurrentlyInside;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
