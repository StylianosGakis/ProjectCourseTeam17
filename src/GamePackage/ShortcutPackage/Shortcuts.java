/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Friday, 07/12/2018 at 21:18
 */

package GamePackage.ShortcutPackage;

import java.util.*;

public abstract class Shortcuts {

    /**
     * Use to build a String that shows all the menu choices of that HashMap
     */
    protected static String getAllMenuChoices(HashMap<String, Character> map) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("\n");
        for (String key : map.keySet()) {
            returnString.append(getMenuChoice(key, map));
        }
        returnString.append("\n");
        return returnString.toString();
    }

    /**
     * Used to build a String that has all the key-binds and their usage
     */
    protected static String getAllKeyBinds(HashMap<String, Character> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Character> entry : map.entrySet()) {
            stringBuilder.append("Key-bind for [" + entry.getKey() + "] is [" + entry.getValue() + "]\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Used to get all the values from the HashMap.
     * Usually to check if another character is contained in one of these values with .contains on the returned Array.
     */
    protected static List<Character> getAllHashMapValues(HashMap<String, Character> map) {
        return new ArrayList<>(map.values());
    }

    /**
     * Used to get one of the menu choices and print it along with the connected key-binds.
     */
    protected static String getMenuChoice(String key, HashMap<String, Character> map) {
        return getValue(key, map) + ". " + key + "\n";
    }

    /**
     * Used to get one specific value (key-bind) from one key specifically.
     * Usually to check input and compare it to check which input the user put.
     */
    protected static Character getValue(String key, HashMap<String, Character> map) {
        return map.get(key);
    }

    /**
     * @param key      The key of which the value we want to change
     * @param newValue The new value that the key will be linked with
     * @return Whether the action was done properly or not
     */
    protected static boolean setValue(String key, Character newValue, HashMap<String, Character> map) {
        if (map.containsValue(newValue)) {
            String oldUsed = getKeyFromValue(newValue, map);
            System.out.println("Key-bind [" + newValue + "] is already in use by: " + oldUsed);
            return false;
        } else {
            map.replace(key, newValue);
            System.out.println("Success! Action [" + key + "] is now bound to [" + newValue + "]");
            return true;
        }
    }

    protected static String getKeyFromValue(Character value, HashMap<String, Character> map) {
        // Can only use this since we guarantee a 1-to-1 mapping on our HashMap, so we just get the first match.
        for (Map.Entry<String, Character> entry : map.entrySet()) { // loop through all entries inside our map.
            if (Objects.equals(value, entry.getValue())) { // if map's value == new value we are trying to insert.
                return entry.getKey(); // get the key of that entry, and save it on oldUsed to print it under.
            }
        }
        return null; // Should never reach this point since HashMap is 1-to-1 and we always check our input
    }
}
