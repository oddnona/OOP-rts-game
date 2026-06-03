package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Tyrell.
 * This soldier has custom stats reflecting the strength and pride of House Tyrell.
 */
public class TyrellSoldier extends Soldier {
    /**
     * Creates a new Tyrell soldier with preset health and damage values.
     */
    public TyrellSoldier(){
        armyType = ArmyType.TYRELL_SOLDIER;
        name = "Tyrell Soldier";
        health=130;
        damage=25;
        description = " Tyrell Rose Knights. 'Growing strong' - Healthy and graceful, the heartland of" +
                " Westerosi chivalry."+
                " Richly adorned with polished blades and full plate armor, these men are sturdy."+
                " The roses battle in style: ruthless, traditional, highly disciplined.";
    }
}

