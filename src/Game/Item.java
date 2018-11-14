/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Wednesday, 14/11/2018 at 00:33
 */

package Game;

public abstract class Item {
    // Field variables
    private String name;

    // Constructors
    public Item(String name) {
        this.name = name;
    }

    // Methods

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
