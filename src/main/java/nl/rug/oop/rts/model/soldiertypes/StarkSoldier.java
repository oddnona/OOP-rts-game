package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Stark.
 * This soldier has custom stats reflecting Stark discipline and resilience.
 */
public class StarkSoldier extends Soldier {
    /**
     * Creates a new Stark soldier with preset health and damage values.
     */
    public StarkSoldier(){
        armyType = ArmyType.STARK_SOLDIER;
        name = "Stark Soldier";
        health=115;
        damage=40;
        description = " Stark Wolves. 'Winter is coming' - Strong, united, loyal and grounded." +
                " Knighthood is a southern thing. True northern warriors do not need titles to prove their grit."+
                " When the snows fall and the white winds blow, the lone wolf dies but the pack survives.";
    }
}