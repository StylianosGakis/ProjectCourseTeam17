package GamePackage.ItemsStuff;

public class Loot extends Item{
    // Field variables
    /**
     * Maybe do have a name for all the loot, but mention it's name and its worth in coins. Could instead have it take
     * space in the inventory and only count the loot that we were able to take with us to the exit instead of just
     *
     */
    private int worthInCoins;

    // Constructors
    public Loot(String name, int worthInCoins) {
        super(name);
        this.worthInCoins = worthInCoins;
    }

    // Methods

    // Getters and setters
    public int getWorthInCoins() {
        return worthInCoins;
    }
    public void setWorthInCoins(int worthInCoins) {
        this.worthInCoins = worthInCoins;
    }
    @Override
    public String toString() {
        return super.toString() +
                "WorthInCoins = " + worthInCoins;
    }
}
