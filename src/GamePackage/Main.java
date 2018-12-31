package GamePackage;

import GamePackage.HelperClassPackage.Help;

public class Main {
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.runMyProgram();
    }

    public void runMyProgram() {
        Game game = new Game();
        game.playMusic(true, "ambientStandard.wav");
        Character choice = printMainMenu();
        if (choice == '1') {
            game.startGame();
        } else if (choice == '2') {
            //TODO make a menu from a list of loaded games to show and pick from, also show some relevant information
            //  from each loaded game
            Game loadedGame = Help.loadGame();
            loadedGame.runGame();
        } else {
            System.exit(0);
        }
    }

    private Character printMainMenu() {
        System.out.print(
                "\nOnly the brave and the mad dare to enter these cursed lands, searching for the legendary treasures that lie ahead." +
                        "\nBut beware, adventurer, a lot of brave souls sought the riches of the land, but none kept their heads in the end." +
                        "\nIf you want the gold and the glory, you must prove your skill in fierce battles and your wit in deadly situations." +
                        "\nAnd remember:" +
                        "\nThe night is here and it is full of terrors." +
                        "\nAre you ready to enter the dungeon of Maragoth?\n");
        System.out.print("\n1. BRING IT ON! (Start game)\n" +
                "2. Load game\n" +
                "3. Nope. (Quit game)\n");
        return Help.readChar();
    }
}
