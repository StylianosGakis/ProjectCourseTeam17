/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:58
 */

package GamePackage;

public abstract class Creature {
    // Field variables
    private String name;
    private int[][] roomCurrentlyInside;
    private int maxHealth;
    private int currentHealth;
    private int damage;

    // Constructors
    public Creature(String name, int[][] roomCurrentlyInside, int maxHealth, int damage) {
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
    public int[][] getRoomCurrentlyInside() {
        return roomCurrentlyInside;
    }
    public void setRoomCurrentlyInside(int[][] roomCurrentlyInside) {
        this.roomCurrentlyInside = roomCurrentlyInside;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
