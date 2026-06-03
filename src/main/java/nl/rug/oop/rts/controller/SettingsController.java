package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.view.SettingsPanel;
import nl.rug.oop.rts.view.Sounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that controls the settings panel.
 */
public class SettingsController implements ActionListener {
    /**
     * the settings panel.
     */
    private SettingsPanel settingsPanel;
    /**
     * the game.
     */
    private Game game;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the controller.
     * @param settingsPanel the settings panel.
     * @param game the game.
     */
    public SettingsController(SettingsPanel settingsPanel, Game game) {
        this.settingsPanel = settingsPanel;
        this.game = game;
        sounds = new Sounds();
        settingsPanel.getLoadGame().addActionListener(this);
        settingsPanel.getNewGame().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == settingsPanel.getNewGame()) {
            sounds.playSound("buttonsound.wav");
            game.createNewGame();
        }
    }
}
