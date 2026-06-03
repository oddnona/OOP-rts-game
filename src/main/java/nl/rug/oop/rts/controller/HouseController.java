package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.model.House;
import nl.rug.oop.rts.view.HousePanel;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that controls the house panel.
 */
public class HouseController implements ActionListener{
    /**
     * the house panel.
     */
    private HousePanel housePanel;
    /**
     * the game model to create the player.
     */
    private Game game;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the controller.
     * @param housePanel the house panel.
     * @param game the game model.
     */
    public HouseController(HousePanel housePanel, Game game) {
        this.game = game;
        this.housePanel = housePanel;
        sounds = new Sounds();
        for(JButton button : housePanel.getButtons()) {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for(JButton button : housePanel.getButtons()) {
            if(actionEvent.getSource() == button) {
                sounds.playSound("bannersound.wav");
                game.createPlayer(House.values()[housePanel.getButtons().indexOf(button)]);
            }
        }
    }
}
