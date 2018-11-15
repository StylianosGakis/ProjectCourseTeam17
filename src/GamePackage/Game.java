/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package GamePackage;

import java.util.Arrays;

public class Game {
    // Field variables

    /**
     * @param debug could be used to print some values for debugging purposes, false if no extra printing has to happen.
     */
     public static boolean debug = false;

    private Room[][] mapArray;
    private Door[][] verticalDoorArray;
    private Door[][] horizontalDoorArray;
    private byte mapSize = 4;

    // Setup stuff
    /**
     * Method called from main method that manages the order that stuff has to be done to start the game
     * Package private (that's why it's missing public)
     */
    void startGame() {
        setupGame();
        gameLoop();
    }

    /**
     * All the initial stuff that has to be done in order for the game to be setup well, like initializing the map etc.
     */
    private void setupGame() {
        createWorld();
    }

    /**
     * Will be called inside setupGame to create our map
     */
    private void createWorld() {
        createMap();
        insertDoors();
        placeMonstersAndItems();
    }

    private void createMap() {
        //TODO add variables to make the size of the map flexible
        this.mapArray = new Room[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapArray[i][j] = new Room(i, j);
            }
        }
        if (debug) {
            System.out.println("mapArray looks like this");
            System.out.println(Arrays.deepToString(mapArray));
        }
    }

    private void insertDoors() {
        // doors = ( size * size - size ) * 2 for example if size is 4, we have 24 doors, 12 vertical and 12 horizontal
        // Information about Door[][] arrays inside Door class

        // Initialize all as unlocked and no color for now TODO add randomness to keep some entrances closed?
        // Vertical doors
        verticalDoorArray = new Door[mapSize - 1][mapSize]; // Create the vertical door array
        for (int i = 0; i < verticalDoorArray.length; i++) {
            for (int j = 0; j < verticalDoorArray[0].length; j++) {
                verticalDoorArray[i][j] = new Door(false);
            }
        }
        // Horizontal doors
        horizontalDoorArray = new Door[mapSize][mapSize - 1]; // Create the horizontal door array
        for (int i = 0; i < horizontalDoorArray.length; i++) {
            for (int j = 0; j < horizontalDoorArray[0].length; j++) {
                horizontalDoorArray[i][j] = new Door(false);
            }
        }

        if (debug) {
            System.out.println("verticalDoorArray looks like this");
            System.out.println(Arrays.deepToString(verticalDoorArray));
            System.out.println("horizontalDoorArray looks like this");
            System.out.println(Arrays.deepToString(horizontalDoorArray));
        }
    }

    /**
     * Will be called inside setupGame to place everything in the correct positions TODO add randomness to this part.
     */
    private void placeMonstersAndItems() {
        // TODO add new monsters, initially fixed positions and fixed monsters, possibly fixed items as well.
    }

    // Other methods

    /**
     * Reminder that
     * verticalDoorArray = new Door[mapSize - 1][mapSize];
     * horizontalDoorArray = new Door[mapSize][mapSize - 1];
     * therefore we can get out of bounds when looking for some doors
     * @param room The room that we are searching in reference to.
     * @param direction To which direction of the referenced room we are searching at.
     * @return returns the door if it exists, otherwise null TODO should check what we are doing with this null return.
     */
    private Door getDoor(Room room, short direction) {
        // TODO this method needs to be tested for all inputs AFTER the doors have been inserted (insertDoors())
        // Reminder
        // If we want 🡹 door of a room[x][y], we do verticalDoorArray[x+1][y]
        // If we want 🡻 door of a room[x][y], we do verticalDoorArray[x][y]
        // If we want 🡸 door of a room[x][y], we do horizontalDoorArray[x][y-1]
        // If we want 🡺 door of a room[x][y], we do horizontalDoorArray[x][y]
        try {
            Door door = null;
            switch (direction){
                case 0: // Direction.LEFT
                    door = horizontalDoorArray[room.getxIndex()][room.getyIndex() - 1];
                    break;
                case 1: // Direction.UP
                    door = verticalDoorArray[room.getxIndex() + 1][room.getyIndex()];
                    break;
                case 2: // Direction.RIGHT
                    door = horizontalDoorArray[room.getxIndex()][room.getyIndex()];
                    break;
                case 3: // Direction.DOWN
                    door = verticalDoorArray[room.getxIndex()][room.getyIndex()];
                    break;
            }
            return door;
        } catch (ArrayIndexOutOfBoundsException outOfBounds){
            // TODO print that we are outisde of the array? Maybe say there is no door there, try and show to the user.
            outOfBounds.printStackTrace();
            return null;
        }
    }

    /**
     * Added from the UML TODO might use for moving the hero, we will see how we will implement this l
     * }ater
     */
    private void moveHero() {

    }

    /**
     * @param musicPath should be given in the format of: "Music/fileName.wav"
     */
    private void playMusic(String musicPath) {
        Thread myThread = new Thread(new MusicPlayer(musicPath));
        myThread.start();
    }

    /**
     * TODO Print the world as ASCII
     * TODO possibly print ONLY the explored parts and not the entire map
     */
    private void showWorldAsASCII() {

    }

    // Running game method

    /**
     * The infinite loop that our already setup game will work with.
     */
    private void gameLoop() {
        while (true) {
         System.exit(10);
        }
    }


}
