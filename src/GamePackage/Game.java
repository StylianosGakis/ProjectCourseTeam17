/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package GamePackage;

import GamePackage.CreaturesStuff.Hero;
import GamePackage.CreaturesStuff.HeroClass;
import GamePackage.MapStuff.Map;
import GamePackage.Music.MusicPlayer;
import Shortcuts.Shortcuts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    /**
     * @param debug could be used to print some values for debugging purposes, false if no extra printing has to happen.
     */
    public static boolean debug = false;
    // Field variables
    private Scanner in = new Scanner(System.in);
    private Map map;
    private Hero hero;

    // Setup stuff
    /**
     * Method called from main method that manages the order that stuff has to be done to start the game
     * Package protected (that's why it's missing public)
     */
    void startGame() {
        setupGame();
        runGame();
    }

    /**
     * All the initial stuff that has to be done in order for the game to be setup well, like initializing the map etc.
     */
    private void setupGame() {
        createMap();
        createHero();
        placeMonstersAndItems();
    }

    private void createMap() {
        System.out.print("Enter size of map\n>> ");
        boolean exit = false;
        int mapSizeChoice = 1;
        while (!exit) {
            try {
                mapSizeChoice = in.nextInt();
                exit = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                in.nextLine(); // To catch the hanging "Enter" from the user
            }
        }
        this.map = new Map(mapSizeChoice);
    }

    private void createHero() {
        // We start our hero on TOP LEFT position.
        int initialStartingPosition = 0;
        hero = new Hero("Hero name",
                map.getRoom(initialStartingPosition, initialStartingPosition),
                10, 10, HeroClass.WARRIOR);
        hero.getRoomCurrentlyInside().setExplored(true);
    }

    /**
     * Will be called inside setupGame to place everything in the correct positions TODO add randomness to this part.
     */
    private void placeMonstersAndItems() {
        // TODO add new monsters, initially fixed positions and fixed monsters, possibly fixed items as well.
    }

    // Other methods

    /**
     * @param musicPath should be given in the format of: "MusicFiles/fileName.wav"
     */
    private void playMusic(String musicPath) {
        Thread myThread = new Thread(new MusicPlayer(musicPath));
        myThread.start();
    }

    /**
     * The infinite loop that our already setup game will work with.
     */
    private void runGame() {
        while (true) {
            testingMovement();
            System.exit(10);
        }
    }

    private void testingMovement() {
        boolean exit = false;
        while (!exit) {
            try {
                map.printMap(hero.getRoomCurrentlyInside().getxIndex(),hero.getRoomCurrentlyInside().getyIndex());
                System.out.println(
                        Shortcuts.mapLeft + ". LEFT\n" +
                        Shortcuts.mapUp + ". UP\n" +
                        Shortcuts.mapRight + ". RIGHT\n" +
                        Shortcuts.mapDown + ". DOWN"
                );
                int direction = in.nextInt();
                // TODO print a different message here if the input does not match any of the shortcuts
                map.moveCreature(hero, direction);
            } catch (InputMismatchException e) {
                System.out.println("Enter a number instead.");
                in.nextLine(); // To catch the hanging "Enter" from the user
            }
        }
    }
}
