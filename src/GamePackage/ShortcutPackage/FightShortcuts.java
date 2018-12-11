/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Monday, 10/12/2018 at 16:50
 */

package GamePackage.ShortcutPackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class FightShortcuts extends Shortcuts {
    public final static String ATTACK = "Attack enemy";
    public final static String INVENTORY = "Open inventory";
    public final static String FLEE = "Flee fight";

    private static HashMap<String, Character> map = new LinkedHashMap<>() {{
        put(ATTACK, '1');
        put(INVENTORY, '2');
        put(FLEE, '3');
    }};

    // Private constructor to prevent from creating useless objects of this class.
    private FightShortcuts() {
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