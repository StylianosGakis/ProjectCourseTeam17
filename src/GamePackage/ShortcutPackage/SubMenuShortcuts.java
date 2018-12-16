/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Friday, 07/12/2018 at 21:50
 */

package GamePackage.ShortcutPackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SubMenuShortcuts extends Shortcuts {
    public final static String INSTRUCTIONS = "Show game instructions";
    public final static String SAVE_GAME = "Save game";
    public final static String SHOW_KEY_BINDINGS = "Show key bindings";
    public final static String CHANGE_KEY_BINDINGS = "Change key bindings";
    public final static String QUIT_GAME = "Quit game";
    public final static String BACK_TO_MAIN_MENU = "Back to main menu";

    private static HashMap<String, Character> map = new LinkedHashMap<>() {{
        put(INSTRUCTIONS, '1');
        put(SAVE_GAME, '2');
        put(SHOW_KEY_BINDINGS, '3');
        put(CHANGE_KEY_BINDINGS, '4');
        put(QUIT_GAME, '5');
        put(BACK_TO_MAIN_MENU, '6');
    }};

    // Private constructor to prevent from creating useless objects of this class.
    private SubMenuShortcuts() {
    }

    public static String getKeyFromValue(Character value) {
        return getKeyFromValue(value, map);
    }

    public static String getAllKeyBinds() {
        return getAllKeyBinds(map);
    }

    public static List<Character> getAllHashMapValues() {
        return getAllHashMapValues(map);
    }

    public static String getAllMenuChoices() {
        return getAllMenuChoices(map);
    }

    public static String getMenuChoice(String key) {
        return getMenuChoice(key, map);
    }

    public static Character getValue(String key) {
        return getValue(key, map);
    }

    public static boolean setValue(String key, Character newValue) {
        return setValue(key, newValue, map);
    }
}