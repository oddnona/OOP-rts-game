package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing the Free Folk.
 * This warrior has custom stats reflecting the raw strength and survival skills of the Free Folk.
 */
public class FreeFolkWarrior extends Soldier {
    /**
     * Creates a new Free Folk warrior with preset health and damage values.
     */
    public FreeFolkWarrior(){
        armyType = ArmyType.FREE_FOLK_WARRIOR;
        name = "Free Folk Warrior";
        health=100;
        damage=65;
        description = " Free Folk Warriors. Free by nature, strong by choice, beyond the Wall's control." +
                " Will charge an enemy twice their number. They call them Wildlings for a reason. They"+
                " fight not for glory, but for freedom and survival, and their strength lies in that determination.";
    }
}
