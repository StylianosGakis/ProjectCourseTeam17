package GamePackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.runMyProgram();
    }

    public void runMyProgram() {
        Game game = new Game();
        int choice = printMainMenu();
        if (choice == 1) {
            game.startGame();
        } else {
            System.exit(0);
        }
    }

    private int printMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("1. Start game\n" +
                "2. Quit game\n");
        System.out.println("Enter your choice");
        System.out.print(">> ");
        int returnValue = in.nextInt();
        in.nextLine();
        return returnValue;
    }
}
