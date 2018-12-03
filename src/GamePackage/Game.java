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
import GamePackage.Shortcuts.Shortcuts;

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
        System.out.println("Are you boy or a girl? b/g");
        boolean genderBoy;
        if (in = "b"){
            genderBoy = true;
        } else {
            genderBoy = false;
        }
        System.out.println("What is your name?");
        String name = in.next;
        System.out.println("Which class are you?");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        int heroClassChoice = in.nextInt;
        hero = new Hero(name,
                map.getRoom(initialStartingPosition, initialStartingPosition),
                10, 10, HeroClass.WARRIOR);
        if (heroClassChoice = 2)
            hero.setHeroClass = MAGE;
        if (heroClassChoice = 3)
            hero.setHeroClass = ROGUE;
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
            exitGame();
            testingMovement();
            System.exit(10);
        }
    }

    private void testingMovement() {
        boolean exit = false;
        while (!exit) {
            try {
                map.printMap(hero.getRoomCurrentlyInside().getxIndex(), hero.getRoomCurrentlyInside().getyIndex());
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

    private int printMainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Game background"); //soon to be written
        System.out.println("1. Start game\n" +
                "2. Quit game\n");
        System.out.println("Enter your choice");
        System.out.print(">>");
        int userInput = input.nextInt();
        return userInput;
    }

    private int printGameMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("MENU");
        System.out.println(
                "1. Move\n" +
                        "2. Pick up item\n" +
                        "3. Drop item\n" +
                        "4. Fight\n" + //shows when there is a monster in the room
                        "5. Flee\n" + //shows when there is a monster in the room
                        "6. Game options\n");//opens sub menu
        System.out.println("Enter your choice");
        System.out.print(">>");
        int menuChoice = input.nextInt();
        if (menuChoice == 6) {
            //sub menu
            System.out.println("1. Show game instructions\n" +
                    "2. Load game\n" +
                    "3. Save game\n" +
                    "4. Show keyboard commands\n" +
                    "5. Change keyboard commands\n" +
                    "6. Quit game\n");
            System.out.println("Enter your choice");
            System.out.print(">>");
            int subMenuChoice = input.nextInt();
            return subMenuChoice + 10;
        }
        return menuChoice;
    }

    private void exitGame() {
        int mainMenuChoice = printMainMenu();
        if (mainMenuChoice == 2) {
            System.exit(0);
        }else{
            printGameMenu();
        }
    }
}


