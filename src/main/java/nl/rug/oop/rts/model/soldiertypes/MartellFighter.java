package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Martell.
 * This fighter has custom stats reflecting the agility and resilience of House Martell.
 */
public class MartellFighter extends Soldier {
    /**
     * Creates a new Martell fighter with preset health and damage values.
     */
    public MartellFighter(){
        armyType = ArmyType.MARTELL_FIGHTER;
        name = "Martell Fighter";
        health=110;
        damage=46;
        description = " Martell Vipers.'Unbowed, unbent, unbroken' - They would rather die" +
                " than surrender. These are vicious fighters who outlive"+
                " enemies. Known for blinding speed, cunning attacks, and poisoned weapons."+
                " Personal pride can make a fighter very valuable in the field.";
    }

}
