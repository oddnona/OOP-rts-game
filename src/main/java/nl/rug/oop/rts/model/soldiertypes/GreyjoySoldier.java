package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Greyjoy.
 * This soldier has custom stats reflecting the Greyjoys' ferocity and precision.
 */
public class GreyjoySoldier extends Soldier {
    /**
     * Creates a new Greyjoy soldier with preset health and damage values.
     */
    public GreyjoySoldier() {
        armyType = ArmyType.GREYJOY_SOLDIER;
        name = "Greyjoy soldier";
        health=100;
        damage=28;
        description = " Greyjoy Krakens. 'What is dead may never die, but rises again, harder and stronger'" +
                "  - Relentless and aggressive, the ironborn come from a long tradition of "+
                " raiding and naval warfare. Fighting is a matter of strength, salt, and steel,"+
                " not ceremony or honor.";
    }
}
