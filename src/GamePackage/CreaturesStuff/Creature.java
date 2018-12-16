/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:58
 */

package GamePackage.CreaturesStuff;

import GamePackage.Game;
import GamePackage.MapStuff.Map;
import GamePackage.MapStuff.Room;

import java.io.Serializable;
import java.security.SecureRandom;

public abstract class Creature implements Serializable {
    // Field variables
    private String name;
    private int xIndex;
    private int yIndex;
    private int lastXIndex;
    private int lastYIndex;
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
    public int getXIndex() {
        return xIndex;
    }
    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }
    public int getYIndex() {
        return yIndex;
    }
    public void setYIndex(int yIndex) {
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
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }
    public int getDamage() {
        SecureRandom rand = new SecureRandom();

        double doubleDamage = (double) this.damage;
        //todo doubleDamage += this.weapon.getDamage();
        double randomValue = rand.nextDouble() / 2; // gets a number from 0 to 0.50
        randomValue -= 0.25; // to put it from -0.25 to +0.25
        doubleDamage *= (1 + randomValue); // times: 0.75 to 1.25

        return (int) doubleDamage; // box to int, might lose some values but it's fine
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Room getOldRoom(Map map) {
        return map.getRoom(this.lastXIndex, this.lastYIndex);
    }

    public void setRoomCurrentlyInside(Map map, Room room) {
        // Take old room and remove this creature from there.
        map.getRoom(this.xIndex, this.yIndex).getCreaturesList().remove(this);

        this.lastXIndex = this.xIndex;
        this.lastYIndex = this.yIndex;

        this.xIndex = (room.getXIndex());
        this.yIndex = (room.getYIndex());

        // Add this creature to the new room.
        room.getCreaturesList().add(this);
    }

    public void heal(int healValue) {
        this.currentHealth += healValue;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }

        System.out.println("Current HP/Max HP - " + this.currentHealth + "/" + this.maxHealth);
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
