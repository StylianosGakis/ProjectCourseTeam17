/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package GamePackage;

import GamePackage.CreaturesStuff.Hero;
import GamePackage.CreaturesStuff.HeroClass;
import GamePackage.ItemsStuff.Item;
import GamePackage.ItemsStuff.Loot;
import GamePackage.MapStuff.Map;
import GamePackage.MenusPackage.Menu;
import GamePackage.Music.MusicPlayer;
import GamePackage.Shortcuts.Shortcuts;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    /*
    TODO - create a method that will receive some parameters, and return back a number but will loop forever until an
    integer is inserted
    */
    /**
     * @param debug could be used to print some values for debugging purposes, false if no extra printing has to happen.
     */
    public static boolean debug = false;

    // Field variables
    private Scanner in = new Scanner(System.in);
    private Map map;
    private Hero hero;

    private SecureRandom rand = new SecureRandom();
    private int turnCounter = 1;


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
        placeMonsters();
        placeItems();
    }

    private void createMap() {
        System.out.print("Enter size of map\n>> ");
        boolean exit = false;
        int mapSizeChoice = 1;
        while (!exit) {
            try {
                mapSizeChoice = in.nextInt();
                in.nextLine();
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
        System.out.print("Are you boy or a girl?\n1. Boy\n2. Girl\n>> ");
        boolean genderChoice;
        if (in.nextInt() == 1) {
            genderChoice = true;
        } else {
            genderChoice = false;
        }
        in.nextLine();
        System.out.print("What is your name?\n>> ");
        String name = in.next();
        System.out.println("Which class are you?");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        int heroClassChoice = in.nextInt();
        in.nextLine();
        HeroClass heroClass = null;
        if (heroClassChoice == 1) {
            heroClass = HeroClass.WARRIOR;
        } else if (heroClassChoice == 2) {
            heroClass = HeroClass.MAGE;
        } else if (heroClassChoice == 3) {
            heroClass = HeroClass.ROGUE;
        }
        this.hero = new Hero(name,
                map.getRoom(initialStartingPosition,
                        initialStartingPosition), 10, 10, heroClass, genderChoice);
        hero.getRoomCurrentlyInside().setExplored(true);
    }

    /**
     * Will be called inside setupGame to place everything in the correct positions TODO add randomness to this part.
     */
    private void placeMonsters() {
        // TODO add new monsters, initially fixed positions and fixed monsters.
    }
    private void placeItems(){
        //Loot
        ArrayList<String> LootName = new ArrayList<>();
        LootName.add("WorriorFoot");
        LootName.add("ChickenNuget");
        LootName.add("WitchHart");
        LootName.add("Monsterteeth");

        ArrayList<Item> itemsList = map.getRoom(0, 0).getItemsList();
        itemsList.add(new Loot(LootName.get(rand.nextInt(LootName.size()) ) , rand.nextInt(100) + 50));
        int size = map.getMapSize();
        System.out.println(map.getRoom(0, 0).getItemsList().get(0));


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
            playMusic("MusicFiles/smb_world_clear.wav");
            System.out.println("Start of turn: " + turnCounter);
            boolean inMenu = true;
            while (inMenu) {
                int menuChoice = Menu.mainMenu();
                inMenu = handleMenuChoice(menuChoice);
            }
            //TODO rest of turn. Here monsters move etc. then the loop goes back and we are on next turn

            turnCounter++; // increase turn number at the end of loop before we go to the next turn
        }
    }

    /**
     * @param menuChoice the choice the user made
     * @return returning true continues the printing, false exits the submenu
     */
    private boolean handleMenuChoice(int menuChoice) {
        boolean inSubMenu = false;

        if (menuChoice == 1) {
            boolean usedTurn = handleMovement();
            if (usedTurn) {
                return false; // end the turn if the move was made
            }
        } else if (menuChoice == 2) {
            // pickup item
        } else if (menuChoice == 3) {
            // drop item
        } else if (menuChoice == 4) {
            // fight
        } else if (menuChoice == 5) {
            // flee
        } else if (menuChoice == 6) {
            inSubMenu = true; // enter the sub menu loop
        }
        while (inSubMenu) {
            int subChoice = Menu.subMenu();
            inSubMenu = handleSubMenu(subChoice);
        }
        return true;
    }

    /**
     * @param subChoice the choice the user made
     * @return returning true continues the printing, false exits the submenu
     */
    private boolean handleSubMenu(int subChoice) {
        if (subChoice == 1) {
            // show game instructions
        } else if (subChoice == 2) {
            // load game
        } else if (subChoice == 3) {
            // save game
        } else if (subChoice == 4) {
            // show keyboard commands
        } else if (subChoice == 5) {
            // change keyboard commands
        } else if (subChoice == 6) {
            exitGame();
        } else if (subChoice == 7) {
            return false;
        }
        return true;
    }

    /**
     * @return returns whether the movement was done successfully.
     */
    private boolean handleMovement() {
        boolean inLoop = true;
        while (inLoop) {
            try {
                map.printMap(hero.getRoomCurrentlyInside().getxIndex(), hero.getRoomCurrentlyInside().getyIndex());
                System.out.print(Shortcuts.cancelAction + ". Cancel move\n" +
                        Shortcuts.mapLeft + ". Left\n" +
                        Shortcuts.mapUp + ". Up\n" +
                        Shortcuts.mapRight + ". Right\n" +
                        Shortcuts.mapDown + ". Down\n" +
                        ">> ");
                int direction = in.nextInt();
                in.nextLine();
                if (direction == Shortcuts.cancelAction) {
                    System.out.println("Alright chief, cancel that");
                    return false; // didn't use a turn.
                } else if (direction != (Shortcuts.mapDown)
                        && direction != (Shortcuts.mapUp)
                        && direction != (Shortcuts.mapRight)
                        && direction != (Shortcuts.mapLeft)) {
                    System.out.println("Your hand must have slipped, maybe you should change your key-bindings!");
                } else {
                    map.moveCreature(hero, direction);
                    inLoop = false; // if we move properly exit
                    return true;
                }
                // TODO else, enable the change of key-bindings from this menu as well
            } catch (InputMismatchException e) {
                System.out.println("Enter a number instead.");
                in.nextLine(); // To catch the hanging "Enter" from the user
            }
        }
        return false;
    }


    private void exitGame() {
        System.out.println("Are you sure? (Y)es/(N)o");
        String exitChoice = in.nextLine();
        // Matches currently Y,y,Yes,yes,Yea,yea,Yeah,Yeap,yeah,yeap
        String pattern = "^[Yy]{1}((es){0,1}|(ea){0,1}[hp]{0,1})$"; //if curious: https://regex101.com/r/AAw9Ry/1
        if (exitChoice.matches(pattern)) {
            System.exit(0);
        }
    }
}


