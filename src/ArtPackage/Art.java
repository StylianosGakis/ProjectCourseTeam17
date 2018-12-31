/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Friday, 30/11/2018 at 00:49
 */

package ArtPackage;

/**
 * Why final?
 * <p>
 * A final class cannot be extended by any other class
 * A final variable cannot be reassigned another value
 * A final method cannot be overridden
 * <p>
 * We are going to use this class just to access it for printing some ASCII art, we don't want to create objects of it
 * nor do we want any other class to inherit it etc. It's purely to make the code in the rest of the classes look
 * cleaner by getting rid of the possible multi-line ASCII art that we might have for our game.
 */
public final class Art {
    private Art() {
    }

    public static void printMap() {
        System.out.println("\n" +
                "88,dPYba,,adPYba,  ,adPPYYba, 8b,dPPYba,\n" +
                "88P'   \"88\"    \"8a \"\"     `Y8 88P'    \"8a\n" +
                "88      88      88 ,adPPPPP88 88       d8\n" +
                "88      88      88 88,    ,88 88b,   ,a8\"\n" +
                "88      88      88 `\"8bbdP\"Y8 88`YbbdP\"'\n" +
                "                              88\n" +
                "                              88");
    }
}
