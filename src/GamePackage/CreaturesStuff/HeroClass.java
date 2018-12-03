/*
 * Enum created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Wednesday, 14/11/2018 at 00:30
 */

package GamePackage.CreaturesStuff;

public enum HeroClass {
    WARRIOR, MAGE, ROGUE
    int hitChance;
}



public class Warrior extends Hero{
    public Warrior(int maxHealth, int damage, int hitChance){
        super(maxHealth + 10, damage, .8);
        hitChance = this.hitChance;
    }
}

public class Mage extends Hero{
    public Warrior(int maxHealth, int damage, int hitChance){
        super(maxHealth + 3, damage + 3, 1);
    }
}

public class Rogue extends Hero{
    public Warrior(int maxHealth, int damage, int hitChance){
        super(maxHealth, damage + 4, .9);
    }
}

