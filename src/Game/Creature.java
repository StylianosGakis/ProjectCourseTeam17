/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:58
 */

package Game;

public abstract class Creature {
    // Field variables
    private String name;
    private int roomCurrentlyInside;
    private int health;
    private int damage;

    // Constructors
    public Creature(String name, int roomCurrentlyInside, int health, int damage) {
        this.name = name;
        this.roomCurrentlyInside = roomCurrentlyInside;
        this.health = health;
        this.damage = damage;
    }

    // Methods

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRoomCurrentlyInside() {
        return roomCurrentlyInside;
    }
    public void setRoomCurrentlyInside(int roomCurrentlyInside) {
        this.roomCurrentlyInside = roomCurrentlyInside;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
