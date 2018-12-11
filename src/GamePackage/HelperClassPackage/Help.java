/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Saturday, 08/12/2018 at 01:18
 */

package GamePackage.HelperClassPackage;

import GamePackage.ShortcutPackage.FightShortcuts;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.MapShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;

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

    public static void clearScreen() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
