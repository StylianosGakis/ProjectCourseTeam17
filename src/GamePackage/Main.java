package GamePackage;

public class Main {
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.runMyProgram();
    }

    public void runMyProgram() {
        Game game = new Game();
        game.startGame();
    }
}
