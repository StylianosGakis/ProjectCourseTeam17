/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Saturday, 08/12/2018 at 01:18
 */

package GamePackage.HelperClassPackage;

import GamePackage.Game;
import GamePackage.ShortcutPackage.FightShortcuts;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.MapShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;
import HighScorePackage.HighScore;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class to save all static helper methods
 */
public final class Help {

    private static final Scanner in = new Scanner(System.in);

    /**
     * Our own "readChar" method to be used in our game whenever an input should be taken that uses a shortcut.
     *
     * @return returns the Character that was inputted by the user
     */
    public static Character readChar() {
        System.out.println("Enter your choice");
        System.out.print(">> ");
        String input = in.nextLine();
        while (input.length() != 1) {
            if (input.length() == 0) {
                System.out.println("Enter at least 1 character");
            } else {
                System.out.println("Enter only 1 character");
            }
            System.out.print(">> ");
            input = in.nextLine();
        }
        return input.charAt(0);
    }

    /**
     * Same as readChar but it checks until the input matches on of the Characters given in the parameters
     *
     * @param characters The characters that the input has to match to. At least one of them
     * @return The character that was finally matched
     */
    public static Character readCharUntilOneMatch(Character... characters) {
        // Turn into List of Characters so we can use .contains
        List<Character> characterList = Arrays.asList(characters);

        Character choice = readChar();
        while (!characterList.contains(choice)) {
            System.out.println("Enter one of the proper choices");
            choice = readChar();
        }
        return choice;
    }

    /**
     * Same as the above class, but accepts a List<Character> directly instead of a bunch of single Characters
     *
     * @param characterList The characters that the input has to match to. At least one of them
     * @return The character that was finally matched
     */
    public static Character readCharUntilOneMatch(List<Character> characterList) {
        Character choice = readChar();
        while (!characterList.contains(choice)) {
            System.out.println("Enter one of the proper choices");
            choice = readChar();
        }
        return choice;
    }

    public static void saveHighScore(HighScore highScore) {
        HighScore oldHighScore = Help.getHighScore();
        boolean doSave = true;
        if (oldHighScore != null) {
            if (oldHighScore.getScore() > highScore.getScore()) { // if the old score was better.
                doSave = false; // don't save since the best one is already saved.
            }
        }
        if (doSave) {
            File file = new File("src/HighScorePackage/HighScore.txt");
            try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file))) {
                oOut.writeObject(highScore);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Save game failed");
            }
        }
    }

    public static HighScore getHighScore() {
        File file = new File("src/HighScorePackage/HighScore.txt");
        HighScore highScore = null;
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file))) {
            highScore = (HighScore) oIn.readObject();
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
        return highScore;
    }

    public static void saveGame(Game game) {
        File file = new File("src/SaveGames/Save_1.txt");
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file))) {
            oOut.writeObject(game);
            System.out.println("\nGame Saved!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Game loadGame() {
        File file = new File("src/SaveGames/Save_1.txt");
        Game game = null;
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file))) {
            game = (Game) oIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    /**
     * Prints game instructions for the player to read
     */
    public static void printGameInstructions() {
        clearScreen();
        System.out.println(
                "The goal of the game is to reach the room that contains the exit.\n" +
                        "Once there, you have a choice to exit the game or not keeping your high score.\n" +
                        "You are allowed to move around the map to fight more monsters and acquire more loot.\n" +
                        "The menu options are descriptive about what your options are while in game.\n" +
                        "There is also a sub-menu that includes the rest of the moves available to you.\n" +
                        "\nPress any key to continue"
        );
        in.nextLine();
    }

    /**
     * Prints all the keybinds from all the shortcut classes, uses the private methods inside this class for each case.
     */
    public static void printAllKeyBindings() {
        System.out.println("--- Main menu options ---");
        printMainMenuKeyBindings();
        System.out.println("--- Sub menu options ---");
        printSubMenuKeyBindings();
        System.out.println("--- Map menu options ---");
        printMapMenuKeyBindings();
        System.out.println("--- Fighting options ---");
        printFightingKeyBindings();
    }


    private static void printMainMenuKeyBindings() {
        System.out.println(MainMenuShortcuts.getAllKeyBinds());
    }

    private static void printSubMenuKeyBindings() {
        System.out.println(SubMenuShortcuts.getAllKeyBinds());
    }

    private static void printMapMenuKeyBindings() {
        System.out.println(MapShortcuts.getAllKeyBinds());
    }

    private static void printFightingKeyBindings() {
        System.out.println(FightShortcuts.getAllKeyBinds());
    }

    /**
     * Handles the change of the keybinds, uses the private methods inside this class for each case.
     */
    public static void handleChangeOfKeyBinds() {
        System.out.println("--- Current key-bindings ---");
        Help.printAllKeyBindings();
        System.out.println();
        System.out.println("Which key bindings would you like to change");
        System.out.println("1. Main menu\n2. Sub menu\n3. Map menu\n4. Fighting menu\n5. Cancel change");
        Character choice = Help.readCharUntilOneMatch('1', '2', '3', '4', '5');
        if (choice == '1') {
            changeMainMenuKeyBindings();
        } else if (choice == '2') {
            changeSubMenuKeyBindings();
        } else if (choice == '3') {
            changeMapMenuKeyBindings();
        } else if (choice == '4') {
            changeFightingKeyBindings();
        } else if (choice == '5') {
            System.out.println("Key-bind change has been canceled");
        }
    }

    private static void changeMainMenuKeyBindings() {
        System.out.println("Which key-bind do you want to change");
        printMainMenuKeyBindings();
        Character keyBindChoice = readCharUntilOneMatch(MainMenuShortcuts.getAllHashMapValues());
        System.out.println("What do you want the new key-bind to be");
        Character newKeyBind = readChar();
        if (!MainMenuShortcuts.setValue(MainMenuShortcuts.getKeyFromValue(keyBindChoice), newKeyBind)) {
            System.out.println("The change has failed, canceling change");
        }
    }

    /**
     * Have 3 separate private methods as I don't know how to send a Class as a parameter
     */
    private static void changeSubMenuKeyBindings() {
        System.out.println("Which key-bind do you want to change");
        printSubMenuKeyBindings();
        Character keyBindChoice = readCharUntilOneMatch(SubMenuShortcuts.getAllHashMapValues());
        System.out.println("What do you want the new key-bind to be");
        Character newKeyBind = readChar();
        if (!SubMenuShortcuts.setValue(SubMenuShortcuts.getKeyFromValue(keyBindChoice), newKeyBind)) {
            System.out.println("The change has failed, canceling change");
        }
    }

    private static void changeMapMenuKeyBindings() {
        System.out.println("Which key-bind do you want to change");
        printMapMenuKeyBindings();
        Character keyBindChoice = readCharUntilOneMatch(MapShortcuts.getAllHashMapValues());
        System.out.println("What do you want the new key-bind to be");
        Character newKeyBind = readChar();
        if (!MapShortcuts.setValue(MapShortcuts.getKeyFromValue(keyBindChoice), newKeyBind)) {
            System.out.println("The change has failed, canceling change");
        }
    }

    private static void changeFightingKeyBindings() {
        System.out.println("Which key-bind do you want to change");
        printFightingKeyBindings();
        Character keyBindChoice = readCharUntilOneMatch(FightShortcuts.getAllHashMapValues());
        System.out.println("What do you want the new key-bind to be");
        Character newKeyBind = readChar();
        if (!FightShortcuts.setValue(FightShortcuts.getKeyFromValue(keyBindChoice), newKeyBind)) {
            System.out.println("The change has failed, canceling change");
        }
    }

    /**
     * Pseudo-clear screen that simply prints a lot of line brakes since we have no clear screen function in cmd
     */
    public static void clearScreen() {
        for (int i = 0; i < 5; i++) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // 20 line brakes
        }
    }

    /**
     * A method to call to simply make the program stall for milliseconds duration
     *
     * @param milliseconds how many milliseconds the Thread should sleep
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
