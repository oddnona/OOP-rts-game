package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.House;

import java.util.List;
import java.util.Random;

/**
 * event for giving allied armies more soldiers.
 */
public class MilkOfThePoppyEvent extends Event{
    /**
     * constructor.
     *
     * @param house the house the event belongs to.
     */
    public MilkOfThePoppyEvent(House house) {
        super(house);
        oneTimeOnly = false;
        name = "The Infirmary";
        soundName = "milkevent.wav";
    }

    @Override
    public void doEvent(List<Army> armies) {
        Random rand = new Random();
        for(Army army : armies){
            if(house == army.getHouse()){
                int newSoldiers = rand.nextInt(31);
                if(newSoldiers == 0){
                    message = "The Infirmary gave the wounded " + army.getSoldier() +
                            " milk of the poppy but they didn't survive the wounds";
                } else {
                    message = "The Infirmary gave the wounded " + army.getSoldier() +
                            " milk of the poppy and " + newSoldiers + " soldiers healed";
                }
                army.setAmount(army.getAmount() + newSoldiers);
                showPopup();
            }
        }
    }
}
