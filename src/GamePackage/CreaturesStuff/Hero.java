/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Tuesday, 13/11/2018 at 22:14
 */

package GamePackage.CreaturesStuff;

import GamePackage.MapStuff.Room;

public class Hero extends Creature {
    // Field variables
    private HeroClass heroClass;
    private boolean isBoy;
    // TODO add keys
    // TODO add backpack

    // Constructors
    public Hero(String name, Room roomCurrentlyInside, int maxHealth, int damage, HeroClass heroClass, boolean isBoy) {
        super(name, roomCurrentlyInside, maxHealth, damage);
        this.heroClass = heroClass;
    }


    // Methods
    // TODO add method for using one of the items, possibly different methods for items/food or even one will see.

    // Getters and setters
    public HeroClass getHeroClass() {
        return heroClass;
    }
    public boolean isBoy() {
        return isBoy;
    }
}
