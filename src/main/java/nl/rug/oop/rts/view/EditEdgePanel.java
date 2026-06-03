package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.AddArmyObserver;
import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Player;
import nl.rug.oop.rts.view.Buttons.LeftButton;
import nl.rug.oop.rts.view.Buttons.RightButton;
import nl.rug.oop.rts.view.Buttons.soldierButtons.SoldierButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 * panel for editing the edge's information.
 */
public class EditEdgePanel extends JPanel implements AddArmyObserver {
    /**
     * the edge to be edited.
     */
    private Edge edge;
    /**
     * the text field to edit the node's nm.
     */
    private JTextArea armyDescription;
    /**
     * blank space atop the menu.
     */
    private JTextArea justSpace;
    /**
     * the buttons for the army type.
     */
    private SoldierButton soldierButton;
    /**
     * a map that connects the army type with the respective soldier button.
     */
    private Map<ArmyType, SoldierButton> soldierMap;
    /**
     * the panel for the soldier buttons.
     */
    private JPanel soldierPanel;
    /**
     * index for the displayed button.
     */
    private int buttonSelected;
    /**
     * the left arrow.
     */
    private LeftButton leftButton;
    /**
     * the right arrow.
     */
    private RightButton rightButton;
    /**
     * buttons for add, remove and show an event from the edge.
     */
    private JButton addEvent, removeEvent, showEvents;
    /**
     * background image for the menu.
     */
    private final Image background;
    /**
     * the player instance.
     */
    private Player player;

    /**
     * constructor for the edit edge panel.
     * @param edge the edge to be edited.
     * @param soldierMap the map of soldier buttons.
     * @param player the player instance.
     */
    public EditEdgePanel(Edge edge, Map<ArmyType, SoldierButton> soldierMap, Player player) {
        this.soldierMap = soldierMap;
        this.edge = edge;
        this.player = player;
        buttonSelected = 0;
        background = TextureLoader.getInstance().getTexture(
                Path.of("images", "effects", "edgemenu.jpg"), 1000, 2500
        );
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        soldierPanel = new JPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        setup(c);
    }

    /**
     * set up layout for menu items.
     * @param c location object for panel placement.
     */
    private void setup(GridBagConstraints c){
        soldierPanel = new JPanel();
        soldierPanel.setLayout(new BorderLayout());
        soldierPanel.setOpaque(false);
        leftButton = new LeftButton();
        rightButton = new RightButton();
        soldierPanel.add(leftButton, BorderLayout.WEST);
        soldierPanel.add(rightButton, BorderLayout.EAST);
        onAddedArmy();
        add(soldierPanel, c);
        JPanel buttonPanel = new JPanel();
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        addEvent = makeControlButton("Add Event");
        removeEvent = makeControlButton("Remove Event");
        showEvents = makeControlButton("Show Events");
        if(edge.getHouse() != player.getHouse()) {
            addEvent.setEnabled(false);
            removeEvent.setEnabled(false);
            showEvents.setEnabled(false);
        }
        buttonPanel.add(addEvent);
        buttonPanel.add(removeEvent);
        buttonPanel.add(showEvents);
        add(buttonPanel, c);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * creates a custom font for the descriptions of the armies.
     * @return the new font.
     */
    private Font createCustomFont() {
        Font font;
        try {
            String file = "src/main/resources/fonts/Monotype-Corsiva-Regular.ttf";
            font = Font.createFont(Font.TRUETYPE_FONT, new File(file)).deriveFont(Font.PLAIN, 18);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(file)));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return font;
    }

    /**
     * creates a description for the army to display it on the panel.
     * @param txt the description of the army.
     * @return the view of the description in the form of a TextField.
     */
    private JTextArea createDescription(String txt) {
        JTextArea label = new JTextArea(txt);
        label.setWrapStyleWord(true);
        label.setLineWrap(true);
        label.setEditable(false);
        label.setFocusable(false);
        label.setOpaque(false);
        label.setFont(createCustomFont());
        label.setForeground(Color.WHITE);
        label.setRows(10);
        return label;
    }

    /**
     * custom buttons.
     * @param txt the text on the button.
     * @return the button.
     */
    private JButton makeControlButton(String txt) {
        JButton b = new JButton(txt);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        return b;
    }

    /**
     * getter for the edge.
     * @return the edge.
     */
    public Edge getEdge() {
        return edge;
    }

    /**
     * getter for the left button.
     * @return the left button.
     */
    public LeftButton getLeftButton() {
        return leftButton;
    }

    /**
     * getter for the right button.
     * @return the right button.
     */
    public RightButton getRightButton() {
        return rightButton;
    }

    /**
     * getter for the index of the selected button.
     * @return the current selected button index;
     */
    public int getButtonSelected() {
        return buttonSelected;
    }

    /**
     * setter for the index of the selected button.
     * @param buttonSelected the new index of the selected button.
     */
    public void setButtonSelected(int buttonSelected) {
        this.buttonSelected = buttonSelected;
    }

    /**
     * getter for the list of soldier buttons.
     * @return the list of soldier buttons.
     */
    public SoldierButton getSoldierButton() {
        return soldierButton;
    }

    /**
     * getter for the add event button.
     * @return the add button.
     */
    public JButton getAddEvent() {
        return addEvent;
    }

    /**
     * getter for the remove event button.
     * @return the remove button.
     */
    public JButton getRemoveEvent() {
        return removeEvent;
    }

    /**
     * getter for the show event button.
     * @return the show event button.
     */
    public JButton getShowEvents() {
        return showEvents;
    }

    @Override
    public void onAddedArmy() {
        if(soldierButton != null) {
            soldierPanel.remove(soldierButton);
        }
        if(!edge.getArmies().isEmpty()) {
            while(buttonSelected >= edge.getArmies().size()) {
                buttonSelected--;
            }
            soldierButton = soldierMap.get(edge.getArmies().get(buttonSelected).getSoldier().getArmyType());
            soldierPanel.add(soldierButton, BorderLayout.CENTER);
            String description = edge.getArmies().get(buttonSelected).getAmount()
                    + edge.getArmies().get(buttonSelected).getSoldier().getDescription();
            if(armyDescription != null) {
                soldierPanel.remove(armyDescription);
            }
            armyDescription = createDescription(description);
            soldierPanel.add(armyDescription, BorderLayout.SOUTH);
        } else {
            buttonSelected = 0;
            if(soldierButton != null) {
                soldierPanel.remove(soldierButton);
            }
            if(armyDescription != null) {
                soldierPanel.remove(armyDescription);
            }
        }
        revalidate();
        repaint();
    }
}
