/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package GamePackage;

import GamePackage.CreaturesStuff.Hero;
import GamePackage.CreaturesStuff.HeroClass;
import GamePackage.CreaturesStuff.Monster;
import GamePackage.HelperClassPackage.Help;
import GamePackage.ItemsStuff.Item;
import GamePackage.ItemsStuff.Loot;
import GamePackage.MapStuff.Map;
import GamePackage.MapStuff.Room;
import GamePackage.MenusPackage.MenuPrints;
import GamePackage.Music.MusicPlayer;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.MapShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public static Hero hero;
    public static Map map;

    // Field variables

    private static Scanner in = new Scanner(System.in);
    private SecureRandom rand = new SecureRandom();
    private int turnCounter = 1;
    ArrayList<Item> inventory = new ArrayList<>();
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
        System.out.print("Enter size of map (4 or more is mandatory for a better experience)\n>> ");
        boolean exit = false;
        int mapSizeChoice = 1;
        while (!exit) {
            try {
                mapSizeChoice = in.nextInt(); // Have to use nextInt instead of Game.getChar() because we want over 9
                in.nextLine();
                if (mapSizeChoice < 4) {
                    System.out.println("Minimum map size automatically initiated as 4");
                    mapSizeChoice = 4;
                }
                exit = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                in.nextLine(); // To catch the hanging "Enter" from the user
            }
        }
        System.out.println("Map size picked: " + mapSizeChoice);
        map = new Map(mapSizeChoice);
    }

    private void createHero() {
        // We start our hero on top left position.
        int initialStartingPosition = 0;
        int startingHealth = 100;
        int startingDamage = 10;

        System.out.println("Choose character gender\n1. Male\n2. Female");
        Character genderInput = Help.readCharUntilOneMatch('1', '2');
        boolean genderChoice = (genderInput == '1');
        System.out.println("Gender picked: " + (genderChoice ? "Male" : "Female"));
        System.out.print("What is your name?\n>> ");
        String name = in.nextLine();
        System.out.println("Name saved: " + name);
        System.out.println("Which class are you?");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        HeroClass heroClass = null;
        Character classChoice = Help.readCharUntilOneMatch('1', '2', '3');
        if (classChoice == '1') {
            heroClass = HeroClass.WARRIOR;
        } else if (classChoice == '2') {
            heroClass = HeroClass.MAGE;
        } else if (classChoice == '3') {
            heroClass = HeroClass.ROGUE;
        }
        System.out.println("Class picked: " + heroClass);
        hero = new Hero(name,
                initialStartingPosition,
                initialStartingPosition,
                startingHealth,
                startingDamage,
                heroClass,
                genderChoice, startingHealth, inventory);
        map.getRoom(hero).setExplored(true);
        map.getRoom(hero).getCreaturesList().add(hero);
    }

    /**
     * Will be called inside setupGame to place monsters in the correct positions
     */
    private void placeMonsters() {
        int minHealth = 40; // edit here
        int maxHealth = 80; // edit here
        int minDamage = 4; // edit here
        int maxDamage = 8; // edit here
        maxDamage -= minDamage;
        maxHealth -= minHealth;

        //Monster names
        ArrayList<String> monsterName = new ArrayList<>(
                Arrays.asList("Ogre Magi", "Spirit Breaker", "Ember Spirit", "Earth Spirit")
        );

        System.out.println("Chose amount of monsters in map \n1. Low Amount\n2. Medium Amount\n3. High Amount");
        Character choice = Help.readCharUntilOneMatch('1', '2', '3');
        int numChoice = Character.getNumericValue(choice);
        // Multiple of 1 if low, multiple of 2 if med, multiple of 3 if high.
        // Example, if map is 5x5. low = 5 monsters, medium = 10 monsters, high = 15 monsters.
        int numberOfMonstersToSpawn = map.getMapSize() * numChoice;

        for (int i = 0; i < numberOfMonstersToSpawn; i++) { // TODO stop adding more creatures if tha map is full
            Room randomRoom = map.getRandomRoom(); // gets a random room from our map
            // If it tries to spawn inside of a room that already has another creature (or the hero) inside, don't.
            if (randomRoom.getCreaturesList().isEmpty()) {
                // adds to the item arraylist of that room
                randomRoom.getCreaturesList()
                        .add(new Monster(monsterName.get(rand.nextInt(monsterName.size())),
                                randomRoom.getxIndex(),
                                randomRoom.getyIndex(),
                                rand.nextInt(minHealth) + maxHealth,
                                rand.nextInt(minDamage) + maxDamage,
                                null));
            } else {
                i--; // Decrease the counter so that we don't skip a monster, we just put it to another place.
            }
        }
    }


    /**
     * Will be called inside setupGame to place items in the correct positions
     */
    private void placeItems() {
        int lootMinValue = 100; // edit here
        int lootMaxValue = 150; // edit here
        lootMaxValue -= lootMinValue;

        //Loot names
        ArrayList<String> LootName = new ArrayList<>();
        LootName.add("Warrior Foot");
        LootName.add("Chicken Nugget");
        LootName.add("Witch Heart");
        LootName.add("Monster Tooth");

        System.out.println("Chose amount of loot in map \n1. Low Amount\n2. Medium Amount\n3. High Amount");
        Character choice = Help.readCharUntilOneMatch('1', '2', '3');
        int numChoice = Character.getNumericValue(choice);

        // Multiple of 1 if low, mult of 2 if med, mult of 3 if high.
        // Example, if map is 5x5. low = 5 items, medium = 10 items, high = 15 items.
        int numberOfItemsToBeMade = map.getMapSize() * numChoice;

        for (int i = 0; i < numberOfItemsToBeMade; i++) {
            Room randomRoom = map.getRandomRoom(); // gets a random room from our map
            // adds to the item arraylist of that room
            randomRoom.getItemsList()
                    .add(new Loot(LootName.get(rand.nextInt(LootName.size())),
                            rand.nextInt(lootMinValue) + lootMaxValue));
        }
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
                Character menuChoice = MenuPrints.mainMenu();
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
    private boolean handleMenuChoice(Character menuChoice) {
        boolean inSubMenu = false;

        if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.MOVE)) { // 1. Move
            boolean successfulMove = handleMovement();
            if (successfulMove) {
                System.out.println("New map status:\n");
                map.printMap();
                return false; // end the turn if the move was made
            }
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.PICK)) { // 2. pickup items
            showItemsInRoom();
            // pickup item
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.DROP)) { // 3. drop items
            placeMonsters();
            // drop item
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.OPTIONS)) { // 4. Open game options
            // enter the sub menu loop
            inSubMenu = true;
        } else if (menuChoice == 5) { // TODO remove this from here and put on fighting menu
            // fight
            System.out.println(map.getRoom(hero).getCreaturesList());
        } else if (menuChoice == 6) { // TODO remove this from here and put on fighting menu
            // flee
        }
        while (inSubMenu) {
            Character subChoice = MenuPrints.subMenu();
            inSubMenu = handleSubMenu(subChoice);
        }
        return true;
    }

    /**
     * @param subChoice the choice the user made
     * @return returning true continues the printing, false exits the submenu
     */
    private boolean handleSubMenu(int subChoice) {
        if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.INSTRUCTIONS)) { // show game instructions

        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.SAVE_GAME)) { // save game
            //TODO add save function
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.LOAD_GAME)) { // load game
            //TODO add load function
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.SHOW_KEY_BINDINGS)) { // show keyboard commands
            Help.printAllKeyBindings();
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.CHANGE_KEY_BINDINGS)) { // change keyboard commands
            Help.handleChangeOfKeyBinds();
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.QUIT_GAME)) {
            exitGame();
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.BACK_TO_MAIN_MENU)) {
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
            //Art.printMap();
            map.printMap();
            System.out.print(MapShortcuts.getAllMenuChoices());
            Character direction = Help.readChar();
            if (!MapShortcuts.getAllHashMapValues().contains(direction)) {
                System.out.println("Your hand must have slipped, maybe you should change your key-bindings?");
                return false;
            } else if (direction == MapShortcuts.getValue(MapShortcuts.CANCEL)) {
                System.out.println("Canceling movement");
                return false; // didn't use a turn.
            } else {
                map.moveCreature(hero, direction);
                inLoop = false; // if we move properly exit
                return true;
            }
        }
        return false;
    }

    private void showItemsInRoom() {
        Room currentRoom = map.getRoom(hero);
        if (currentRoom.getItemsList().isEmpty()) {
            System.out.println("There are no items in this room!");
        } else {
            System.out.print("This is the list of items in this room:\n");
            for (Item element : currentRoom.getItemsList()) {
                System.out.println(element);
            }
            System.out.println("Would you like to pick one up?");
            // TODO add non-troll picking-up method that shows all items and user can choose what to pick up.
            System.out.println("Well even if you wanted to, you can't yet.");
            System.out.print("Press F to continue\n>> ");
            String keyPressed = in.nextLine();
            // https://regex101.com/r/S7oMZL/1
            while (!keyPressed.matches("([Ff]){1,}")) {
                System.out.print("That's not F!\n>> ");
                keyPressed = in.nextLine();
            }
        }
    }

    private void exitGame() {
        System.out.print("Are you sure? (Y)es/(N)o\nEnter your choice\n>> ");
        String exitChoice = in.nextLine();
        // Matches currently Y,y,Yes,yes,Yep,yep,Yea,yea,Yeah,yeah,Yeap,yeap
        String pattern = "^[Yy]{1}((es){0,1}|(ep){0,1}|(ea){0,1}[hp]{0,1})$"; //if curious: https://regex101.com/r/AAw9Ry/1
        if (exitChoice.matches(pattern)) {
            System.exit(0);
        }
    }
}


