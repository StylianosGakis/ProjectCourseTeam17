package GamePackage.MapStuff;

import GamePackage.CreaturesStuff.Creature;
import GamePackage.ItemsStuff.Item;

import java.util.ArrayList;

public class Room {
    // Field variables
    private ArrayList<Item> itemsList = new ArrayList<>();
    private ArrayList<Creature> creaturesList = new ArrayList<>();
    private boolean isExplored;
    private int xIndex;
    private int yIndex;
    // Constructors
    public Room(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    // Methods

    // Getters and setters
    public ArrayList<Item> getItemsList() {
        return itemsList;
    }
    public void setItemsList(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }
    public ArrayList<Creature> getCreaturesList() {
        return creaturesList;
    }
    public void setCreaturesList(ArrayList<Creature> creaturesList) {
        this.creaturesList = creaturesList;
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
    public boolean isExplored() {
        return isExplored;
    }
    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    @Override
    public String toString() {
        return "Room{" +
                "itemsList=" + itemsList +
                ", creaturesList=" + creaturesList +
                ", isExplored=" + isExplored +
                ", xIndex=" + xIndex +
                ", yIndex=" + yIndex +
                '}';
    }
}
