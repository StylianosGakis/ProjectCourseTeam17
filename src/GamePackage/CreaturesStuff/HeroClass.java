/*
 * Enum created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Wednesday, 14/11/2018 at 00:30
 */

package GamePackage.CreaturesStuff;

public enum HeroClass {
    WARRIOR, MAGE, ROGUE
}

public class Warrior extends Hero{
    public Warrior(int maxHealth, int damage){
        super(maxHealth + 10, damage);
    }
}

public class Mage extends Hero{
    public Warrior(int maxHealth, int damage){
        super(maxHealth + 5, damage + 5);
    }
}

public class Rogue extends Hero{
    public Warrior(int maxHealth, int damage){
        super(maxHealth, damage + 10);
    }
}

