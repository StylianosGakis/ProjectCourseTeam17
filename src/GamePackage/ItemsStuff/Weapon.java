package GamePackage.ItemsStuff;

public class Weapon extends Item {
    private int damage;

    // Constructors
    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    // Methods

    // Getters and setters
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return super.toString() + "Damage: " + damage;

    }
}
