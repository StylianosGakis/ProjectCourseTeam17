/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:58
 */

package GamePackage.CreaturesStuff;

import GamePackage.Game;
import GamePackage.MapStuff.Room;

public abstract class Creature {
    // Field variables
    private String name;
    private int xIndex;
    private int yIndex;
    private int maxHealth;
    private int currentHealth;
    private int damage;

    // Constructors
    public Creature(String name, int xIndex, int yIndex, int maxHealth, int damage) {
        this.name = name;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
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
    public int getxIndex() {
        return xIndex;
    }
    public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }
    public int getyIndex() {
        return yIndex;
    }
    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
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

    public void setRoomCurrentlyInside(Room room) {
        // Take old room and remove this creature from there.
        Game.map.getRoom(this.xIndex, this.yIndex).getCreaturesList().remove(this);

        this.setxIndex(room.getxIndex());
        this.setyIndex(room.getyIndex());

        // Add this creature to the new room.
        room.getCreaturesList().add(this);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", xIndex=" + xIndex +
                ", yIndex=" + yIndex +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", damage=" + damage +
                '}';
    }
}
