/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 20:54
 */

package Game;

public class Game {

    /**
     * Will be called inside setupGame to create our map TODO add variables to change the size of the map
     */
    private void createWorld() {

    }

    /**
     * Will be called inside setupGame to place everything in the correct positions TODO add randomness to this part
     */
    private void placeMonstersAndItems() {

    }

    /**
     * Added from the UML, might use for moving the hero, we will see how we will implement this instead
     */
    private void moveHero() {

    }

    /**
     * Method called from main method that manages the order that stuff has to be done to start the game
     */
    private void startGame(){
        setupGame();
        gameLoop();
    }

    /**
     * All the initial stuff that has to be done in order for the game to be setup well, like initializing the map etc.
     */
    private void setupGame(){
        placeMonstersAndItems();
    }

    /**
     * The infinite loop that our already setup game will work with.
     */
    private void gameLoop(){
        while (true){

        }
    }

}
