/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Friday, 07/12/2018 at 21:15
 */

package GamePackage.ShortcutPackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainMenuShortcuts extends Shortcuts {
    public final static String MOVE = "Move hero";
    public final static String PICK = "Pickup item";
    public final static String DROP = "Drop item";
    public final static String OPTIONS = "Game options";
    public final static String MAP = "Show map";
    public final static String FOOD = "Show foods";
    public final static String END_GAME = "Finish the game";


    private static HashMap<String, Character> map = new LinkedHashMap<>() {{
        put(MOVE, '1');
        put(PICK, '2');
        put(DROP, '3');
        put(OPTIONS, '4');
        put(MAP, '5');
        put(FOOD, '6');
        put(END_GAME, '7');
    }};

    // Private constructor to prevent from creating useless objects of this class.
    private MainMenuShortcuts() {
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
