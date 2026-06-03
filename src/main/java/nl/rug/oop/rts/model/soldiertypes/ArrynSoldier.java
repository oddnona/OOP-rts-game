package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Arryn.
 * This soldier has moderate stats, reflecting the noble and defensive nature of the Vale's warriors.
 */
public class ArrynSoldier extends Soldier {
    /**
     * Creates a new Arryn soldier with preset health and damage values.
     */
    public ArrynSoldier(){
        armyType = ArmyType.ARRYN_SOLDIER;
        name = "Arryn Soldier";
        health=105;
        damage=30;
        description = " Arryn Falcons. 'As high as honor' - True, noble, practitioners of chivalry." +
                " Graceful fighters, experienced with rocky terrain. Rigorously prepared and decently armoured,"+
                " even stronger ahorse. These men train hard and they stay just and loyal for life.";
    }
}
