package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * event where the battlefield is full of traps and some of the armies die.
 */
public class TrapedBattlefieldEvent extends Event{
    /**
     * constructor.
     *
     * @param house the house the event belongs to.
     */
    public TrapedBattlefieldEvent(House house) {
        super(house);
        oneTimeOnly = true;
        name = "Traped Battlefield";
        soundName = "losesound.wav";
    }

    @Override
    public void doEvent(List<Army> armies) {
        Random random = new Random();
        List<Army> armiesCopy = new ArrayList<>(armies);
        for(Army army : armiesCopy) {
            if(army.getHouse() != house) {
                eventDone = true;
                int firstAmount = army.getAmount();
                int half = army.getAmount() / 2;
                int left = army.getAmount() - random.nextInt(half);
                int lost = army.getAmount() - left;
                army.setAmount(left);
                message = firstAmount + " " + army.getSoldier() + " walked on a trapped battlefield and "
                        + lost +
                        " of them lost their lives";
                showPopup();
            }
            if(army.getAmount() <= 0) {
                armies.remove(army);
            }
        }
    }
}
