package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing the Faceless Men.
 * This elite assassin has high stats reflecting deadly precision and mysterious training.
 */
public class FacelessMan extends Soldier {
    /**
     * Creates a new Faceless Man soldier with preset health and damage values.
     */
    public FacelessMan() {
        armyType = ArmyType.FACELESS_MAN;
        name = "Faceless Man";
        health = 300;
        damage = 100;
        description = " Faceless Assassins. 'Valar Morghulis' " +
                " Death is a gift. Or a debt. They will grant this mercy with high precision "+
                " and no remorse. Surgical strikes. They kill without struggle, sound, or evidence."+
                " Swordplay is an art, and these shadows are fluid and agile.";
    }
}
