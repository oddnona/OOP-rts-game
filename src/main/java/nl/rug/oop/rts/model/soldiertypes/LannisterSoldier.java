package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Lannister.
 * This soldier has custom stats reflecting the power and precision of Lannister forces.
 */
public class LannisterSoldier extends Soldier {
    /**
     * Creates a new Lannister soldier with preset health and damage values.
     */
    public LannisterSoldier(){
        armyType = ArmyType.LANNISTER_SOLDIER;
        name = "Lannister Soldier";
        health=130;
        damage=27;
        description = " Lannister Lions. 'Hear me roar' - Power conscious people with a large, professional army." +
                " Skilled, tactical, always ruthless, and extremely well decorated, with top-tier weaponry and"+
                " impenetrable golden armor."+
                " Lannisters are the embodiment of martial excellence. ";

    }
}
