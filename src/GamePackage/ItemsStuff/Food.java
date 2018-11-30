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
    private int usesLeft;

    // Constructors
    public Food(String name, int healthRestored, int usesLeft) {
        super(name);
        this.healthRestored = healthRestored;
        this.usesLeft = usesLeft;
    }

    // Methods

    // Getters and setters
    public int getHealthRestored() {
        return healthRestored;
    }
    public void setHealthRestored(int healthRestored) {
        this.healthRestored = healthRestored;
    }
    public int getUsesLeft() {
        return usesLeft;
    }
    public void setUsesLeft(int usesLeft) {
        this.usesLeft = usesLeft;
    }
}
