package GamePackage.MenusPackage;

import GamePackage.HelperClassPackage.Help;
import GamePackage.ShortcutPackage.MainMenuShortcuts;
import GamePackage.ShortcutPackage.SubMenuShortcuts;

public final class MenuPrints {

    private MenuPrints() {
    }

    public static Character mainMenu() {
        System.out.println("\n--- Main menu ---");
        System.out.print(MainMenuShortcuts.getAllMenuChoices());
        return Help.readCharUntilOneMatch(MainMenuShortcuts.getAllHashMapValues());
    }

    public static Character subMenu() {
        System.out.println("\n--- Game options ---");
        System.out.print(SubMenuShortcuts.getAllMenuChoices());
        return Help.readCharUntilOneMatch(SubMenuShortcuts.getAllHashMapValues());
    }
}
