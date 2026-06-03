package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing the King's Guard.
 * This elite soldier has high stats reflecting their unmatched skill and devotion to the crown.
 */
public class KingsGuard extends Soldier {
    /**
     * Creates a new King's Guard soldier with preset health and damage values.
     */
    public KingsGuard() {
        armyType = ArmyType.KINGS_GUARD;
        name = "KingsGuard";
        health=130;
        damage=35;
        description = " Kingsguards - the elite, ceremonial, and political center of" +
                " martial power in Westeros. They are symbols of honor,"+
                " as well as the deadliest swordsmen of the kingdom. Rich and prestigious, the"+
                " City's watch has sworn to protect its people until their dying breath.";
    }
}
