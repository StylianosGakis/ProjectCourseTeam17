package GamePackage;

import java.util.ArrayList;

public class Room {
    // Field variables
    private ArrayList<Item> itemsList = new ArrayList<Item>();
    private ArrayList<Monster> monstersList = new ArrayList<Monster>();
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
}
