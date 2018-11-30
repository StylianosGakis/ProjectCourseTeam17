package GamePackage.MapStuff;

import GamePackage.CreaturesStuff.Monster;
import GamePackage.ItemsStuff.Item;

import java.util.ArrayList;

public class Room {
    // Field variables
    private ArrayList<Item> itemsList = new ArrayList<>();
    private ArrayList<Monster> monstersList = new ArrayList<>();
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
    public ArrayList<Monster> getMonstersList() {
        return monstersList;
    }
    public void setMonstersList(ArrayList<Monster> monstersList) {
        this.monstersList = monstersList;
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
                ", monstersList=" + monstersList +
                ", isExplored=" + isExplored +
                ", xIndex=" + xIndex +
                ", yIndex=" + yIndex +
                '}';
    }
}
