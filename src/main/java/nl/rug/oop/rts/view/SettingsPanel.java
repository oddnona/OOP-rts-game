package nl.rug.oop.rts.view;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

/**
 * class that represents the settings panel, where you start a new game, or load a previous one.
 */
public class SettingsPanel extends JPanel {
    /**
     * the new game button.
     */
    private JButton newGame;
    /**
     * the load game button.
     */
    private JButton loadGame;
    /**
     * background.
     */
    private Image background;

    /**
     * constructor for the panel.
     */
    public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        background = TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "firstscreen.jpg"), 2000, 1010
        );
        newGame = new JButton("Start new Game");
        newGame.setFocusable(false);
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGame.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 15));
        newGame.setBackground(Color.BLACK);
        loadGame = new JButton("Resume Game");
        loadGame.setFocusable(false);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 15));
        loadGame.setBackground(Color.BLACK);
        add(Box.createVerticalGlue());
        add(newGame, BorderLayout.CENTER);
        add(Box.createVerticalStrut(10));
        add(loadGame, BorderLayout.AFTER_LAST_LINE);
        add(Box.createVerticalGlue());
    }

    /**
     * paint component for the background.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    /**
     * getter for the new game button.
     * @return the new game button.
     */
    public JButton getNewGame() {
        return newGame;
    }

    /**
     * getter for the load game button.
     * @return the load game button.
     */
    public JButton getLoadGame() {
        return loadGame;
    }
}
