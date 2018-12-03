package GamePackage.MenusPackage;

import java.util.Scanner;

public final class Menu {
    private static final Scanner in = new Scanner(System.in);

    public static int mainMenu(){
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Move\n" +
                "2. Pick up item\n" +
                "3. Drop item\n" +
                "4. Fight\n" + //shows when there is a monster in the room //TODO print ONLY if monster is here
                "5. Flee\n" + //shows when there is a monster in the room
                "6. Game options\n"); //opens sub menu
        System.out.println("Enter your choice");
        System.out.print(">> ");
        int returnValue = in.nextInt();
        in.nextLine();
        return returnValue;
    }

    public static int subMenu(){
        System.out.println("1. Show game instructions\n" +
                "2. Load game\n" +
                "3. Save game\n" +
                "4. Show keyboard commands\n" +
                "5. Change keyboard commands\n" +
                "6. Quit game\n" +
                "7. Back to previous menu\n");
        System.out.println("Enter your choice");
        System.out.print(">> ");
        int returnValue = in.nextInt();
        in.nextLine();
        return returnValue;
    }
}
