package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing the Unsullied.
 * This soldier has balanced stats reflecting elite training and discipline.
 */
public class Unsullied extends Soldier {
    /**
     * Creates a new Unsullied soldier with preset health and damage values.
     */
    public Unsullied() {
        armyType = ArmyType.UNSULLIED;
        name = "Unsullied";
        health=100;
        damage=30;
        description = " Unsullied fighters - slaves trained in Astapor to fight their owners' wars." +
                " Now they fight for their independence. "+
                "They do not flee. They do not question."+
                " They kill. These people are among the most disciplined, efficient, "+
                "and elite infantry forces of this world.";
    }
}
