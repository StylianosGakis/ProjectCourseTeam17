package GamePackage;

import GamePackage.HelperClassPackage.Help;

public class Main {
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.runMyProgram();
    }

    public void runMyProgram() {
        Game game = new Game();
        Character choice = printMainMenu();
        if (choice == '1') {
            game.startGame();
        } else {
            System.exit(0);
        }
    }

    private Character printMainMenu() {
        System.out.println("1. Start game\n" +
                "2. Quit game\n");
        return Help.readChar();
    }
}
