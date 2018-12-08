package GamePackage.MenusPackage;

import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;
import GamePackage.HelperClassPackage.Help;

public final class MenuPrints {

    private MenuPrints() {
    }

    public static Character mainMenu() {
        System.out.println("--- Main menu ---");
        System.out.print(MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.MOVE) + // Move in the map
                MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.PICK) + // Pickup item todo pickup item
                MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.DROP) + // Drop an item todo drop item
                MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.OPTIONS) + //opens sub menu
                "5. Fight\n" + //shows when there is a monster in the room // TODO move to fight menu
                "6. Flee\n" //shows when there is a monster in the room // TODO move to fight menu
        );
        return Help.readCharUntilOneMatch(MainMenuShortcuts.getValue(MainMenuShortcuts.MOVE),
                MainMenuShortcuts.getValue(MainMenuShortcuts.PICK),
                MainMenuShortcuts.getValue(MainMenuShortcuts.DROP),
                MainMenuShortcuts.getValue(MainMenuShortcuts.OPTIONS)
        );
    }

    public static Character subMenu() {
        System.out.println("--- Game options ---");
        System.out.print(SubMenuShortcuts.getAllMenuChoices());
        return Help.readCharUntilOneMatch(SubMenuShortcuts.getAllHashMapValues());
    }
}
