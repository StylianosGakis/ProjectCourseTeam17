package GamePackage.ItemsStuff;

/**
 * TODO maybe add some temporary strength given for a number of rounds after eating this food, maybe do that for
 * potions instead as another "Item".
 */
public class Food extends Item {
    // Field variables
    private int healthRestored;

    /**
     * Might include usesLeft, and have the items be used more than once, and then make it able to discard it once it
     * has no uses left (or even before that if the player wishes to do so)
     */


    // Constructors
    public Food(String name, int healthRestored) {
        super(name);
        this.healthRestored = healthRestored;

    }

    // Methods

    // Getters and setters
    public int getHealthRestored() {
        return healthRestored;
    }

    public void setHealthRestored(int healthRestored) {
        this.healthRestored = healthRestored;
    }

    public String toString() {
        return super.toString() +
                "Restores: " + healthRestored + "  Health ";
    }
}

