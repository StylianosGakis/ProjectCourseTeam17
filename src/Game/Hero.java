/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 22:14
 */

package Game;

public class Hero extends Creature {
    // Field variables
    private HeroClass heroClass;
    // TODO add keys
    // TODO add backpack

    // Constructors
    public Hero(String name, int roomCurrentlyInside, int health, int damage, HeroClass heroClass) {
        super(name, roomCurrentlyInside, health, damage);
        this.heroClass = heroClass;
    }

    // Methods
    // TODO add method for using one of the items, possibly different methods for items/food or even one will see.

    // Getters and setters
    public HeroClass getHeroClass() {
        return heroClass;
    }
    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }
}
