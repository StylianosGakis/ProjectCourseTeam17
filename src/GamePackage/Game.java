/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package GamePackage;

import GamePackage.CreaturesStuff.Creature;
import GamePackage.CreaturesStuff.Hero;
import GamePackage.CreaturesStuff.HeroClass;
import GamePackage.CreaturesStuff.Monster;
import GamePackage.HelperClassPackage.Help;
import GamePackage.ItemsStuff.Food;
import GamePackage.ItemsStuff.Item;
import GamePackage.ItemsStuff.Loot;
import GamePackage.ItemsStuff.Weapon;
import GamePackage.MapStuff.Map;
import GamePackage.MapStuff.Room;
import GamePackage.MenusPackage.MenuPrints;
import GamePackage.Music.MusicPlayer;
import GamePackage.ShortcutPackage.FightShortcuts;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.MapShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;
import HighScorePackage.HighScore;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.*;

public class Game implements Serializable {

    private static volatile Thread musicThread = new Thread();
    private static Scanner in = new Scanner(System.in);
    private static SecureRandom rand = new SecureRandom();
    // Field variables
    private final int fightDelay = 100;
    public Hero hero;
    public Map map;
    // Matches currently Y,y,Yes,yes,Yep,yep,Yea,yea,Yeah,yeah,Yeap,yeap
    //if curious: https://regex101.com/r/AAw9Ry/1v
    private String yesPattern = "^[Yy]{1}((es){0,1}|(ep){0,1}|(ea){0,1}[hp]{0,1})$"; //todo a No Pattern
    private int turnCounter = 1;

    // Setup stuff
    public boolean showItemsInRoom() {
        Room currentRoom = map.getRoom(this.hero);
        ArrayList<Item> roomItems = currentRoom.getItemsList();
        System.out.println();
        if (roomItems.isEmpty()) {
            System.out.println("There are no items in the room!");
            return false;
        } else {
            System.out.print("This is the list of items in this room:\n");
            for (int i = 0; i < roomItems.size(); i++) {
                System.out.println((i + 1) + ". " + roomItems.get(i));
            }
        }
        return true;
    }

    /**
     * Plays one of the tracks given as a parameter randomly.
     * If there is a track already playing that has not been interrupted it doesn't play over the last track
     * If it is forced however it will force stop the previous track and immediately start with the new track
     *
     * @param force     force stops any previous musics and forces the new music to start instead
     * @param musicPath should be given in the format of: "fileName.wav" and always be inside folder "MusicFiles"
     */
    void playMusic(boolean force, String... musicPath) {
        if (force) {
            if (musicThread != null) {
                musicThread.interrupt();
            }
        }
        SecureRandom rand = new SecureRandom();
        if (!musicThread.isAlive() || musicThread.isInterrupted() || force) {
            int randomChoice = rand.nextInt(musicPath.length); // pick a random song
            musicThread = new Thread(new MusicPlayer("MusicFiles/" + musicPath[randomChoice]));
            musicThread.start();
        }
    }

    private boolean checkDeath(Creature creature) {
        if (hero.getCurrentHealth() <= 0) {
            System.out.println("\nAlas! " + hero.getName() + " died! The monster wins :( (╯°□°）╯︵ ┻━┻");
            endGame(hero.getCurrentHealth());
            return true;
        } else if (creature.getCurrentHealth() <= 0) {
            System.out.println("Amazing! " + hero.getName() + " won!");
            map.getRoom(creature).getCreaturesList().remove(creature);
            return true;
        }
        return false;
    }

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
                in.nextLine();
                System.out.print("Wrong input, please enter a number!\n>> ");
            }
        }
        System.out.println("Map size picked: " + mapSizeChoice);
        map = new Map(mapSizeChoice);
    }

    private void createHero() {
        // We start our hero on top left position.
        int initialStartingPosition = 0;
        int startingHealth = 1;
        int startingDamage = 1;
        int agility = 1;//out of 9 and 9 is 100% success rate

        System.out.print("\nWhat is your name?\n>> ");
        String name = in.nextLine();
        System.out.println("Name saved: " + name);
        System.out.println("\nWhich class are you?");
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

        if (heroClass == HeroClass.WARRIOR) {
            agility = 7;
            startingHealth = 150;
            startingDamage = 10;
        }

        if (heroClass == HeroClass.MAGE) {
            agility = 8;
            startingHealth = 100;
            startingDamage = 8;
        }

        if (heroClass == HeroClass.ROGUE) {
            agility = 9;
            startingHealth = 80;
            startingDamage = 15;
        }

        System.out.println("Class picked: " + heroClass);

        if (heroClass == HeroClass.WARRIOR) {
            agility = 7;
            startingHealth = 150;
            startingDamage = 10;
        }

        if (heroClass == HeroClass.MAGE) {
            agility = 8;
            startingHealth = 100;
            startingDamage = 8;
        }

        if (heroClass == HeroClass.ROGUE) {
            agility = 9;
            startingHealth = 80;
            startingDamage = 15;
        }
        hero = new Hero(name,
                initialStartingPosition,
                initialStartingPosition,
                startingHealth,
                startingDamage,
                heroClass,
                agility);
        map.getRoom(hero).setExplored(true);
        map.getRoom(hero).getCreaturesList().add(hero);
    }

    // Other methods

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

        System.out.println("\nChose amount of monsters in map \n1. Low Amount\n2. Medium Amount\n3. High Amount");
        Character choice = Help.readCharUntilOneMatch('1', '2', '3');
        int numChoice = Character.getNumericValue(choice);
        // Multiple of 1 if low, multiple of 2 if med, multiple of 3 if high.
        // Example, if map is 5x5. low = 5 monsters, medium = 10 monsters, high = 15 monsters.
        int numberOfMonstersToSpawn = map.getMapSize() * numChoice;

        for (int i = 0; i < numberOfMonstersToSpawn; i++) { // TODO stop adding more creatures if tha map is full
            Room randomRoom = map.getRandomRoom(); // gets a random room from our map
            // If it tries to spawn inside of a room that already has another creature (or the hero) inside, don't.
            if (randomRoom.getCreaturesList().isEmpty()) {
                // adds to the item arrayList of that room
                randomRoom.getCreaturesList()
                        .add(new Monster(monsterName.get(rand.nextInt(monsterName.size())),
                                randomRoom.getXIndex(),
                                randomRoom.getYIndex(),
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
        int foodMinHealth = (int) (hero.getMaxHealth() * 0.2);
        int foodMaxHealth = (int) (hero.getMaxHealth() * 0.4);
        foodMaxHealth -= foodMinHealth;
        int[] weaponDamage = {10, 7, 5};

        //Loot names
        ArrayList<String> LootName = new ArrayList<>();
        LootName.add("Warrior Foot");
        LootName.add("Chicken Nugget");
        LootName.add("Witch Heart");
        LootName.add("Monster Tooth");
        ArrayList<String> FoodName = new ArrayList<>();
        FoodName.add("Fish");
        FoodName.add("Bread");
        FoodName.add("Cheese");
        ArrayList<String> NewWeapon = new ArrayList<>();
        NewWeapon.add("Sword of Raemarr");
        NewWeapon.add("Bow of Henya");
        NewWeapon.add("Dagger of Elize");
        //TODO place the items random on map
        System.out.println("\nChose amount of loot in map \n1. Low Amount\n2. Medium Amount\n3. High Amount");
        Character choice = Help.readCharUntilOneMatch('1', '2', '3');
        int numChoice = Character.getNumericValue(choice);

        // Multiple of 1 if low, mult of 2 if med, mult of 3 if high.
        // Example, if map is 5x5. low = 5 items, medium = 10 items, high = 15 items.
        int numberOfItemsToBeMade = map.getMapSize() * numChoice;
        for (int i = 0; i < numberOfItemsToBeMade; i++) {
            Room randomRoomL = map.getRandomRoom(); // gets a random room from our map
            // adds to the item ArrayList of that room
            randomRoomL.getItemsList()
                    .add(new Loot(LootName.get(rand.nextInt(LootName.size())),
                            rand.nextInt(lootMinValue) + lootMaxValue));
            Room randomRoomF = map.getRandomRoom();
            randomRoomF.getItemsList()
                    .add(new Food(FoodName.get(rand.nextInt(FoodName.size())),
                            rand.nextInt(foodMinHealth) + foodMaxHealth));

        }

        for (int i = 0; i < 3; i++) {
            Room room = map.getRandomRoom();
            room.getItemsList().add(new Weapon(NewWeapon.get(i), weaponDamage[i]));
        }
    }

    /**
     * The infinite loop that our already setup game will work with.
     */
    void runGame() {
        while (true) {
            System.out.println("Press any key to start turn: " + turnCounter);
            playMusic(false, "ambientStandard.wav", "waterStandard.wav");
            in.nextLine();
            Help.clearScreen();
            System.out.println("Start of turn: " + turnCounter + "\n");

            hero.printCurrentStats(); // Prints some relevant information at the start of the turn.

            map.printMap(this);
            if (map.getRoom(hero).isExit()) {
                System.out.println("\nYou have found the exit!\n" +
                        "You can exit the game and keep your high score");
                HighScore highScore = Help.getHighScore();
                if (highScore != null) {
                    System.out.println("Current score: " + hero.getScore());
                    System.out.println("Best score:    " + highScore.getScore());
                } else {
                    System.out.println("There is no current high score! You can claim it.");
                }
            }

            boolean inMenu = true;
            while (inMenu) {
                Character menuChoice = MenuPrints.mainMenu(this);
                inMenu = handleMenuChoice(menuChoice);
            }
            checkForFight();

            // Loop through all the Rooms and make all Monsters move
            for (Room[] roomRow : map.mapArray) {
                for (Room room : roomRow) {
                    ArrayList<Creature> creatureList = room.getCreaturesList();
                    if (!creatureList.isEmpty()) {
                        Creature creature = creatureList.get(0);
                        if (creature instanceof Monster) {
                            double chance = rand.nextDouble();
                            if (chance > 0.75) { // 75% chance to move
                                map.moveMonster((Monster) creature);
                            }
                        }
                    }
                }
            }

            turnCounter++; // increase turn number at the end of loop before we go to the next turn
        }
    }

    private void checkForFight() {
        Room currentRoom = map.getRoom(hero);
        Monster monster = null;
        if (currentRoom.getCreaturesList().size() != 2) { // 2 means the hero and 1 more thing which must be monster
            return;
        } else {
            for (Creature creature : currentRoom.getCreaturesList()) { // loop through the array and get the monster
                if (creature instanceof Monster) {
                    monster = (Monster) creature;
                }
            }
        }
        if (monster != null) {
            fight(monster);
        }
    }

    private void fight(Monster monster) {
        playMusic(true, "fightMusic.wav");
        System.out.println("\nAlas! " + hero.getName() + " has encountered a bloodthirsty enemy. " +
                "Now it is time to prove your worth and defeat it!");//todo random encounter quotes

        // Printing health information once before the fight continues
        System.out.println(hero.getName() + ":");
        hero.printCurrentHealth();
        System.out.println(monster.getName() + ":");
        System.out.println("Current HP/Max HP - " + monster.getCurrentHealth() + "/" + monster.getMaxHealth());

        boolean inLoop = true;
        while (inLoop) {
            System.out.print(FightShortcuts.getAllMenuChoices());
            Character actionChoice = Help.readChar();
            if (!FightShortcuts.getAllHashMapValues().contains(actionChoice)) {
                System.out.println("Your hand must have slipped, maybe you should change your key-bindings?");
            } else if (actionChoice == FightShortcuts.getValue(FightShortcuts.INVENTORY)) {
                if (consumeFood()) { // true if USED a potion so much commence with the fight
                    inLoop = fightTurn(monster, actionChoice);
                }
            } else {
                inLoop = fightTurn(monster, actionChoice);
            }
        }
        System.out.println("The fight is over");
        playMusic(true, "ambientStandard.wav", "waterStandard.wav");
    }

    /**
     * @param monster the monster that is fighting the hero
     * @param choice  the menu choice from player
     * @return false if the fight is over
     */
    private boolean fightTurn(Monster monster, char choice) {
        if (choice == FightShortcuts.getValue(FightShortcuts.ATTACK)) {
            //todo random attack quotes
            int heroDamage = hero.getDamage();
            System.out.println(hero.getName() + " prepares for attack!");
            Help.sleep(fightDelay);
            if (hero.getAgility() >= rand.nextInt(10)) {
                System.out.println("Wohoo! " + hero.getName() + " throws a devastating blow dealing " + heroDamage + " damage");
                Help.sleep(fightDelay);
                monster.setCurrentHealth(monster.getCurrentHealth() - heroDamage);
            } else {
                System.out.println("Your attack missed!");
            }
            System.out.println(monster.getName() + " health is now " + monster.getCurrentHealth() + "/" + monster.getMaxHealth());
            boolean stopFight = checkDeath(monster);
            if (stopFight) {
                return false;
            }
        } else if (choice == FightShortcuts.getValue(FightShortcuts.INVENTORY)) {
            // here we wasted a turn on drinking potion
            System.out.println(hero.getName() + " ate some food, " + monster.getName() + " takes advantage of " +
                    "that and jumps to attack without giving any chance to react");
        } else if (choice == FightShortcuts.getValue(FightShortcuts.FLEE)) {
            if (hero.getAgility() >= rand.nextInt(10)) {
                Room lastRoom = hero.getOldRoom(this.map);
                hero.setRoomCurrentlyInside(this.map, lastRoom);
                System.out.println(hero.getName() + " escaped with " + hero.getCurrentHealth() + " health.");
                System.out.println("The map now looks like this");
                map.printMap(this);
                Help.sleep(fightDelay);
                return false;
            } else {
                System.out.println(hero.getName() + " tried to flee but failed, the monster takes advantage of " +
                        "that mistake");
            }
        }
        System.out.println("\nThe enemy prepares to attack!");
        Help.sleep(fightDelay);
        int monsterDamage = monster.getDamage();
        if (hero.getAgility() <= rand.nextInt(100)) {
            System.out.println(monster.getName() + " hit " + hero.getName() + " for " + monsterDamage + " damage");
            Help.sleep(fightDelay);
            hero.setCurrentHealth(hero.getCurrentHealth() - monsterDamage);
            hero.printCurrentHealth();
            checkDeath(monster); // here if hero dies the game will just end otherwise nothing happens.
        } else {
            System.out.println("You managed to dodge his attack!");
        }
        return true;
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
                System.out.println(hero.getName() + " has moved");
                return false; // end the turn since the move was made
            }
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.PICK)) { // 2. pickup items
            pickupItem();
            // pickup item
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.DROP)) { // 3. drop items
            dropItem();
            // drop item
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.OPTIONS)) { // 4. Open game options
            // enter the sub menu loop
            inSubMenu = true;
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.MAP)) {
            map.printMap(this); // simply print the map
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.FOOD)) {
            boolean hasItem = showInventory("FOOD");
            if (hasItem) {
                System.out.print("Do you want to eat one of the foods?\nYes/No\n>> ");
                String answer = in.nextLine();
                if (answer.matches(yesPattern)) {
                    consumeFood();
                }
            }
        } else if (menuChoice == MainMenuShortcuts.getValue(MainMenuShortcuts.END_GAME)) {
            endGame(hero.getCurrentHealth());
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
            Help.printGameInstructions();
        } else if (subChoice == SubMenuShortcuts.getValue(SubMenuShortcuts.SAVE_GAME)) { // save game
            Help.saveGame(this);
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
        //Art.printMap();
        map.printMap(this);
        System.out.print(MapShortcuts.getAllMenuChoices());
        Character direction = Help.readChar();
        if (!MapShortcuts.getAllHashMapValues().contains(direction)) {
            System.out.println("Your hand must have slipped, maybe you should change your key-bindings?");
            return false;
        } else if (direction == MapShortcuts.getValue(MapShortcuts.CANCEL)) {
            System.out.println("Canceling movement");
            return false; // didn't use a turn.
        } else {
            map.moveHero(hero, direction);
            return true;
        }
    }

    /**
     * @param type the type of items looking for (key, loot, food)
     * @return false if found nothing of that type, true if found even one
     */
    private boolean showInventory(String type) {
        if (hero.getInventory().isEmpty()) {
            System.out.println("" + hero.getName() + " has no items!");
            return false;
        } else {
            System.out.print("Inventory:\n");
            if (type.equalsIgnoreCase("ALL")) {
                for (int i = 0; i < hero.getInventory().size(); i++) {
                    System.out.println((i + 1) + ". " + hero.getInventory().get(i));
                }
            } else if (type.equalsIgnoreCase("FOOD")) {
                boolean containsFood = false;
                for (int i = 0; i < hero.getInventory().size(); i++) {
                    if (hero.getInventory().get(i) instanceof Food) {
                        System.out.println((i + 1) + ". " + hero.getInventory().get(i));
                        containsFood = true;
                    }
                }
                return containsFood;
            }
            return true;
        }
    }

    private void dropItem() {
        boolean hasItems = showInventory("ALL");

        Room currentRoom = map.getRoom(hero);

        if (hasItems) {
            System.out.print("Enter the number of the item to drop\n>> ");
        }
        while (hasItems) {
            try {
                int itemChoice = in.nextInt(); // we print the items from 1 not from 0 so all must be adjusted later
                in.nextLine();
                if ((itemChoice < 1) || (itemChoice > hero.getInventory().size())) {
                    System.out.println("There is no such item, enter a proper number\n>> ");
                } else {
                    currentRoom.getItemsList().add(hero.getInventory().get(itemChoice - 1)); // -1 since we get input from 1 from user
                    hero.getInventory().remove(itemChoice - 1);
                    hasItems = false;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.print("Wrong input, please enter a number!\n>> ");
            }
        }
    }

    /**
     * @return true if used one (skip turn) false if not (don't use turn)
     */
    private boolean consumeFood() {
        boolean hasItems = showInventory("FOOD");

        if (hasItems) {
            hero.printCurrentHealth();
            System.out.print("Enter the number of the food to consume or 0 to cancel\n>> ");
        }
        while (hasItems) {
            try {
                int itemChoice = in.nextInt(); // we print the items from 1 not from 0 so all must be adjusted later
                in.nextLine();
                if (itemChoice == 0) {
                    System.out.println("Canceling using item");
                    return false;
                } else if ((itemChoice < 1) || (itemChoice > hero.getInventory().size())) {
                    System.out.println("There is no such item, enter a proper number\n>> ");
                } else {
                    if (hero.getInventory().get(itemChoice - 1) instanceof Food) {
                        consumeFood((Food) hero.getInventory().get(itemChoice - 1));
                        return true;
                    } else {
                        System.out.println("That's not food");
                    }
                    hasItems = false;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.print("Wrong input, please enter a number!\n>> ");
            }
        }
        return false;
    }

    private void consumeFood(Food food) {
        int health = food.getHealthRestored();
        hero.heal(health);
        hero.getInventory().remove(food);
    }

    private void pickupItem() {
        boolean hasItems = showItemsInRoom();
        Room currentRoom;
        ArrayList<Item> roomItems = null;

        if (hasItems) {
            System.out.print("Enter the number of the item to pick up or 0 to cancel\n>> ");
            currentRoom = map.getRoom(hero);
            roomItems = currentRoom.getItemsList();
        }
        while (hasItems) {
            try {
                int itemChoice = in.nextInt(); // we print the items from 1 not from 0 so all must be adjusted later
                in.nextLine();
                if (itemChoice == 0) {
                    return;
                } else if ((itemChoice < 1) || itemChoice > roomItems.size()) {
                    System.out.print("There is no such item, enter a proper number\n>> ");
                } else {
                    Item itemPickedUp = roomItems.get(itemChoice - 1); // - 1 since we print them starting from 1.
                    if (itemPickedUp instanceof Loot) {
                        hero.pickupLoot((Loot) itemPickedUp);
                        System.out.println(itemPickedUp + "has added " + ((Loot) itemPickedUp).getWorthInCoins() +
                                " value to your high score. Your current score is now: " + hero.getScore());
                    } else if (itemPickedUp instanceof Weapon) {
                        if (hero.getWeapon() == null) {
                            hero.setWeapon((Weapon) itemPickedUp);
                            System.out.println("You picked up " + itemPickedUp.getName() + ". Your enemies will feel your fury!");
                        } else {
                            if (hero.getWeapon().getDamage() < ((Weapon) itemPickedUp).getDamage()) {
                                hero.setWeapon((Weapon) itemPickedUp);
                                System.out.println("Greater damage means greater strength. You found a better weapon to help you in your quest!");
                            } else {
                                System.out.println("You decide the new weapon is not worthy and you throw it into abyss.");
                            }
                        }
                    } else {
                        hero.getInventory().add(itemPickedUp);
                        System.out.println(itemPickedUp + " has been picked up.");
                    }
                    roomItems.remove(itemPickedUp);
                    hasItems = false;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.print("Wrong input, please enter a number!\n>> ");
            }
        }
    }

    private void exitGame() {
        System.out.print("Are you sure? (Y)es/(N)o\nEnter your choice\n>> ");
        String exitChoice = in.nextLine();
        if (exitChoice.matches(yesPattern)) {
            Help.saveGame(this);
            System.exit(0);
        }
    }

    private void endGame(int heroHealth) {
        if (heroHealth <= 0) {
            System.out.println(hero.getName() + " has died.");
            System.out.print("Your high would be ");
        } else {
            System.out.println(hero.getName() + " has won the game!");
            System.out.print("Your high score was ");
        }
        System.out.println(hero.getScore());
        if (heroHealth > 0) {
            HighScore highScore = new HighScore(hero.getName(), hero.getScore(), new Date());
            Help.saveHighScore(highScore);
            System.out.println("Your high score was saved");
            Help.sleep(1000);
            System.out.println("The current high score is held by:");
            HighScore currentHighScore = Help.getHighScore();
            int nameLength = currentHighScore.getName().length();
            System.out.println(
                    String.format("%-" + nameLength + "s %5s  %s", "Name", "Score", "Date")
            );
            System.out.println(currentHighScore);
        }
        System.out.print("Press anything to continue ");
        in.nextLine();
        musicThread.interrupt();
        Main main = new Main();
        main.runMyProgram();
    }
}



