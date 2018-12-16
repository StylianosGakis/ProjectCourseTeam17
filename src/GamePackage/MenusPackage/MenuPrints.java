package GamePackage.MenusPackage;

import GamePackage.Game;
import GamePackage.HelperClassPackage.Help;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;

public final class MenuPrints {

    private MenuPrints() {
    }

    public static Character mainMenu(Game game) {
        System.out.println("\n--- Main menu ---");
        if (game.map.getRoom(game.hero).isExit()) { // Just print all the options if we are at the room that ends game
            System.out.print(MainMenuShortcuts.getAllMenuChoices());
            return Help.readCharUntilOneMatch(MainMenuShortcuts.getAllHashMapValues());
        } else { // Do not print the option that includes the exit of the game if not in the final room
            System.out.println(MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.MOVE) +
                    MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.PICK) +
                    MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.DROP) +
                    MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.OPTIONS) +
                    MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.MAP) +
                    MainMenuShortcuts.getMenuChoice(MainMenuShortcuts.FOOD)
            );
            // Accept one of the options (not the exit game option since we are not on the map position that exits
            return Help.readCharUntilOneMatch(MainMenuShortcuts.getValue(MainMenuShortcuts.MOVE),
                    MainMenuShortcuts.getValue(MainMenuShortcuts.PICK),
                    MainMenuShortcuts.getValue(MainMenuShortcuts.DROP),
                    MainMenuShortcuts.getValue(MainMenuShortcuts.OPTIONS),
                    MainMenuShortcuts.getValue(MainMenuShortcuts.MAP),
                    MainMenuShortcuts.getValue(MainMenuShortcuts.FOOD)
            );
        }
    }

    public static Character subMenu() {
        System.out.println("\n--- Game options ---");
        System.out.print(SubMenuShortcuts.getAllMenuChoices());
        return Help.readCharUntilOneMatch(SubMenuShortcuts.getAllHashMapValues());
    }
}
