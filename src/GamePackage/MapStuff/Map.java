/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Thursday, 29/11/2018 at 19:03
 */

package GamePackage.MapStuff;

import GamePackage.CreaturesStuff.Creature;
import GamePackage.CreaturesStuff.Hero;
import GamePackage.Game;
import GamePackage.ShortcutPackage.MapShortcuts;

import java.io.Serializable;
import java.security.SecureRandom;

public class Map implements Serializable {
    private int mapSize;
    private Room[][] mapArray;
    private Door[][] verticalDoorArray;
    private Door[][] horizontalDoorArray;

    // During the construction of the Map object, we also insert the doors and create the entire map.
    public Map(int mapSize) {
        this.mapSize = mapSize;
        createMap();
        insertDoors();
        pickExitRoom();
    }

    /**
     * Picks a room from the bottom right part of the map to be an exit so for example if we have map of
     * <p>
     * *   *   *   *
     * *   *   *   *
     * *   *   *   *
     * *   *   *   *
     * <p>
     * The only rooms that are able to be picked as exit are these, and it's picked randomly
     * <p>
     * *   *   *   *
     * *   *   *   *
     * *   *   E   E
     * *   *   E   E
     */
    private void pickExitRoom() {
        SecureRandom rand = new SecureRandom();
        int halfSize = this.mapSize / 2;
        // Random within range upper, lower is: rand.nextInt(upper - lower) + lower
        Room chosenRoom = this.getRoom(rand.nextInt(mapSize - halfSize) + halfSize,
                rand.nextInt(mapSize - halfSize) + halfSize);
        chosenRoom.setExit(true); // set it as isExit
        System.out.println("The exit room has been placed.");
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

        // Initialize all as unlocked and no color for now
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

    /**
     * @param creature  which Creature is to be moved
     * @param direction which direction it should be moved (from MapShortcuts mapUp,mapDown,mapLeft,mapRight)
     * @return returns true if movement was successful, false if it failed
     */
    // TODO don't let the a monster creature move if there is another monster there
    public boolean moveCreature(Creature creature, Character direction) {
        Door door = getDoor(getRoom(creature), direction);
        if (door == null) {
            if (creature instanceof Hero) {
                System.out.println("There is no door in that direction!");
            }
            return false;
        } else if (door.isLocked()) { // TODO unlocking option if we do have the key, prob only work for hero object.
            if (creature instanceof Hero) {
                System.out.println("That door is locked, you will need the " + door.getDoorColor() + " key to unlock!");
            }
            return false;
        } else { // Door exists, and is unlocked. Move there.
            Room currentRoom = getRoom(creature);
            if (direction == (MapShortcuts.getValue(MapShortcuts.LEFT))) {
                creature.setRoomCurrentlyInside(this, getRoom(currentRoom.getXIndex() - 1, currentRoom.getYIndex()));
            } else if (direction == MapShortcuts.getValue(MapShortcuts.UP)) {
                creature.setRoomCurrentlyInside(this, getRoom(currentRoom.getXIndex(), currentRoom.getYIndex() - 1));
            } else if (direction == MapShortcuts.getValue(MapShortcuts.RIGHT)) {
                creature.setRoomCurrentlyInside(this, getRoom(currentRoom.getXIndex() + 1, currentRoom.getYIndex()));
            } else if (direction == MapShortcuts.getValue(MapShortcuts.DOWN)) {
                creature.setRoomCurrentlyInside(this, getRoom(currentRoom.getXIndex(), currentRoom.getYIndex() + 1));
            }
            if (creature instanceof Hero) {
                getRoom(creature).setExplored(true);
            }
            return true;
        }
    }

    // returns current room creature is inside
    public Room getRoom(Creature creature) {
        return this.getRoom(creature.getXIndex(), creature.getYIndex());
    }

    /**
     * Reminder that
     * verticalDoorArray = new Door[mapSize][mapSize - 1];
     * horizontalDoorArray = new Door[mapSize - 1][mapSize];
     * therefore we can get out of bounds when looking for some doors
     *
     * @param room      The room that we are searching in reference to.
     * @param direction To which direction of the referenced room we are searching at.
     * @return returns the door if it exists, otherwise null.
     */
    public Door getDoor(Room room, Character direction) {
        // Reminder TODO check if the comment is correct or not
        // If we want 🡹 door of a room[x][y], we do verticalDoorArray[x][y-1]
        // If we want 🡻 door of a room[x][y], we do verticalDoorArray[x][y]
        // If we want 🡸 door of a room[x][y], we do horizontalDoorArray[x-1][y]
        // If we want 🡺 door of a room[x][y], we do horizontalDoorArray[x][y]
        try {
            Door door = null;
            if (direction == MapShortcuts.getValue(MapShortcuts.LEFT)) {
                door = horizontalDoorArray[room.getXIndex() - 1][room.getYIndex()];
            } else if (direction == MapShortcuts.getValue(MapShortcuts.UP)) {
                door = verticalDoorArray[room.getXIndex()][room.getYIndex() - 1];
            } else if (direction == MapShortcuts.getValue(MapShortcuts.RIGHT)) {
                door = horizontalDoorArray[room.getXIndex()][room.getYIndex()];
            } else if (direction == MapShortcuts.getValue(MapShortcuts.DOWN)) {
                door = verticalDoorArray[room.getXIndex()][room.getYIndex()];
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
    public void printMap(Game game) {
        int heroY = getRoom(game.hero).getYIndex();
        int heroX = getRoom(game.hero).getXIndex();
        System.out.print("(? = unexplored, * = explored, H = hero, E = Exit, I = Item, M = Monster)\n");
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (y == heroY && x == heroX) {
                    System.out.print("H ");
                } else if (getRoom(x, y).isExit()) {
                    System.out.print("E "); // EXIT SHOW just for now.//Todo not show this normally.
                } else if (!getRoom(x, y).getCreaturesList().isEmpty()) {
                    System.out.print("M "); // MONSTER SHOW just for now.//Todo not show this normally.
                } else if (!getRoom(x, y).getItemsList().isEmpty()) {
                    System.out.print("I "); // ITEM SHOW just for now.//Todo not show this normally.
                } else if (getRoom(x, y).isExplored()) {
                    System.out.print("* ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }

        game.showItemsInRoom();
    }

    /**
     * @return Returns a random room from this map, to be used for random inserts etc.
     */
    public Room getRandomRoom() {
        SecureRandom rand = new SecureRandom();
        return this.getRoom(rand.nextInt(mapSize), rand.nextInt(mapSize));
    }

    // Getters and Setters
    public Room getRoom(int x, int y) {
        return mapArray[x][y];
    }
    public int getMapSize() {
        return mapSize;
    }

}
