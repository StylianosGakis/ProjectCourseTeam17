/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Wednesday, 14/11/2018 at 00:34
 */

package GamePackage;

public class Key extends Item {
    // Field variables
    /**
     * Might use colors to indicate different type of keys for different doors.
     */
    String color;

    // Constructors
    public Key(String name, String color) {
        super(name);
        this.color = color;
    }

    // Methods

    // Getters and setters
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
