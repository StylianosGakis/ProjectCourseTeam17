/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Thursday, 29/11/2018 at 19:03
 */

package GamePackage.MapStuff;

import Art.Art;
import GamePackage.CreaturesStuff.Creature;
import Shortcuts.Shortcuts;

public class Map {
    private int mapSize;
    private Room[][] mapArray;
    private Door[][] verticalDoorArray;
    private Door[][] horizontalDoorArray;
    private boolean debug = true;

    // During the construction of the Map object, we also insert the doors and create the entire map.
    public Map(int mapSize) {
        this.mapSize = mapSize;
        createMap();
        insertDoors();
    }

    private void createMap() {
        this.mapArray = new Room[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapArray[i][j] = new Room(i, j);
            }
        }
    }

    private void insertDoors() {
        // doors = ( size * size - size ) * 2 for example if size is 4, we have 24 doors, 12 vertical and 12 horizontal
        // Information about Door[][] arrays inside Door class

        // Initialize all as unlocked and no color for now TODO add randomness to keep some entrances closed?
        // Vertical doors
        verticalDoorArray = new Door[mapSize][mapSize - 1]; // Create the vertical door array
        for (int i = 0; i < verticalDoorArray.length; i++) {
            for (int j = 0; j < verticalDoorArray[0].length; j++) {
                verticalDoorArray[i][j] = new Door(false);
            }
        }
        // Horizontal doors
        horizontalDoorArray = new Door[mapSize - 1][mapSize]; // Create the horizontal door array
        for (int i = 0; i < horizontalDoorArray.length; i++) {
            for (int j = 0; j < horizontalDoorArray[0].length; j++) {
                horizontalDoorArray[i][j] = new Door(false);
            }
        }
    }

    public void moveCreature(Creature creature, int direction) {
        Door door = getDoor(getCurrentRoom(creature), direction);
        if (door == null) {
            System.out.println("There is no door in that direction!");
        } else if (door.isLocked()) { // TODO add unlocking option if we do have the key
            System.out.println("That door is locked, you will need the " + door.getDoorColor() + " key to unlock!");
        } else { // Door exists, and is unlocked. Move there.
            Room currentRoom = creature.getRoomCurrentlyInside();
            if (direction == Shortcuts.mapLeft){
                creature.setRoomCurrentlyInside(getRoom(currentRoom.getxIndex() - 1, currentRoom.getyIndex()));
            } else if (direction == Shortcuts.mapUp){
                creature.setRoomCurrentlyInside(getRoom(currentRoom.getxIndex(), currentRoom.getyIndex() - 1));
            } else if (direction == Shortcuts.mapRight){
                creature.setRoomCurrentlyInside(getRoom(currentRoom.getxIndex() + 1, currentRoom.getyIndex()));
            } else if (direction == Shortcuts.mapDown){
                creature.setRoomCurrentlyInside(getRoom(currentRoom.getxIndex(), currentRoom.getyIndex() + 1));
            }
            creature.getRoomCurrentlyInside().setExplored(true);
        }
    }

    // returns current room hero is inside
    public Room getCurrentRoom(Creature creature) {
        return creature.getRoomCurrentlyInside();
    }

    /**
     * Reminder that
     * verticalDoorArray = new Door[mapSize][mapSize - 1];
     * horizontalDoorArray = new Door[mapSize - 1][mapSize];
     * therefore we can get out of bounds when looking for some doors
     *
     * @param room The room that we are searching in reference to.
     * @param direction To which direction of the referenced room we are searching at.
     * @return returns the door if it exists, otherwise null.
     */
    public Door getDoor(Room room, int direction) {
        // Reminder
        // If we want 🡹 door of a room[x][y], we do verticalDoorArray[x][y-1]
        // If we want 🡻 door of a room[x][y], we do verticalDoorArray[x][y]
        // If we want 🡸 door of a room[x][y], we do horizontalDoorArray[x-1][y]
        // If we want 🡺 door of a room[x][y], we do horizontalDoorArray[x][y]
        try {
            Door door = null;
            if (direction == Shortcuts.mapLeft){
                door = horizontalDoorArray[room.getxIndex() - 1][room.getyIndex()];
            } else if (direction == Shortcuts.mapUp){
                door = verticalDoorArray[room.getxIndex()][room.getyIndex() - 1];
            } else if (direction == Shortcuts.mapRight){
                door = horizontalDoorArray[room.getxIndex()][room.getyIndex()];
            } else if (direction == Shortcuts.mapDown){
                door = verticalDoorArray[room.getxIndex()][room.getyIndex()];
            }
            return door;
        } catch (ArrayIndexOutOfBoundsException outOfBounds) {
            // Printing that we are out of bounds
            System.out.println("You knock but to no avail, all you see is a wall of stones blocking your path");
            return null;
        }
    }

    /**
     * TODO Print the world as ASCII art
     */
    public void printMap(int heroX, int heroY) {
        Art.printMap();
        System.out.print("(? = unexplored, * = explored, H = hero)\n");
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (y == heroY && x == heroX) {
                    System.out.print("H ");
                } else if (getRoom(x, y).isExplored()) {
                    System.out.print("* ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    // Getters and Setters
    public Room getRoom(int x, int y) {
        return mapArray[x][y];
    }
}
