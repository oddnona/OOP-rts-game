package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.House;

import java.util.List;

/**
 * event that gives the army more armor(base health in our case).
 */
public class ArmorEvent extends Event{
    /**
     * constructor.
     *
     * @param house the house the event belongs to.
     */
    public ArmorEvent(House house) {
        super(house);
        oneTimeOnly = false;
        name = "Armory";
        soundName = "armorevent.wav";
    }

    @Override
    public void doEvent(List<Army> armies) {
        for(Army army : armies) {
            if(army.getHouse() == house && !army.getSoldier().hasArmor()) {
                army.getSoldier().addArmor();
                message = army + " entered the armory and gained 10 more HP";
                eventDone = true;
                showPopup();
            }
        }
    }
}
