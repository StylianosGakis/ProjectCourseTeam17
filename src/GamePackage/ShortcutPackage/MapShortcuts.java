/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Friday, 30/11/2018 at 01:00
 */

package GamePackage.ShortcutPackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Why final?
 * <p>
 * A final class cannot be extended by any other class
 * A final variable cannot be reassigned another value
 * A final method cannot be overridden
 * <p>
 * We don't want to create objects of it nor do we want any other class to inherit it etc. We are going to use this
 * class just to save all of our shortcuts //TODO enable the user to modify these shortcuts
 */
public final class MapShortcuts extends Shortcuts {
    public final static String LEFT = "Move left";
    public final static String UP = "Move up";
    public final static String RIGHT = "Move right";
    public final static String DOWN = "Move down";
    public final static String CANCEL = "Cancel move";

    // The iteration ordering method for this linked hash map: insertion-order that's why LinkedHashMap is used.
    private static HashMap<String, Character> map = new LinkedHashMap<>() {{
        put(CANCEL, 'Q');
        put(LEFT, 'A');
        put(UP, 'W');
        put(RIGHT, 'D');
        put(DOWN, 'S');
    }};

    // Private constructor to prevent from creating useless objects of this class.
    private MapShortcuts() {
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
