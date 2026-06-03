package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Scroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a scroll with history log.
 */
public class ScrollView implements ActionListener, OpenScrollSubject, AddLogObserver{
    /**
     * the image of the scroll.
     */
    private Image image;
    /**
     * list of observers.
     */
    private List<OpenScrollObserver> observers;
    /**
     * information to draw the scroll on the screen.
     */
    private int frame, maxFrames, width, height;
    /**
     * tells if the scroll is opened or closed.
     */
    private boolean open;
    /**
     * the bounds where the scroll can be clicked.
     */
    private Rectangle bounds;
    /**
     * timer to create an animation.
     */
    private Timer timer;
    /**
     * scroll pane to show the history logs.
     */
    private JScrollPane scrollPane;
    /**
     * panel that shows the history logs.
     */
    private JPanel log;

    /**
     * constructor for the scroll class.
     * @param scroll the scroll model.
     */
    public ScrollView(Scroll scroll) {
        log = new JPanel();
        log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
        log.setOpaque(false);
        for(String text : scroll.getLogs()) {
            log.add(createLog(text));
        }
        scrollPane = new JScrollPane(log);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(1600, 100, 220, 320);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(new Color(210, 171, 126, 1));
        scrollPane.getHorizontalScrollBar().setBackground(Color.PINK);
        timer = new Timer(70, this);
        observers = new ArrayList<>();
        width = 417;
        height = 498;
        maxFrames = 5;
        bounds = new Rectangle(1500, 10, width, height);
        frame = 0;
        setOpen(false);
    }

    /**
     * creates a new font for the scroll.
     * @return the custom font.
     */
    public Font createCustomFont() {
        Font font;
        try {
            String file = "src/main/resources/fonts/Monotype-Corsiva-Regular.ttf";
            font = Font.createFont(Font.TRUETYPE_FONT, new File(file)).deriveFont(Font.PLAIN, 18);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(file)));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return font;
    }

    /**
     * converts an image to the buffered image type.
     * @param img the image to be converted.
     * @return the same image but of type BufferedImage.
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    /**
     * getter for the state of the scroll.
     * @return true if it is open and false if not.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * setter for the state of the scroll.
     * @param open the new state of the scroll.
     */
    public void setOpen(boolean open) {
        this.open = open;
        if(!open) {
            image = TextureLoader.getInstance().getTexture(
                    Path.of("images", "spritesheets", "openscroll.png"), width * 6, height
            );
        } else {
            image = TextureLoader.getInstance().getTexture(
                    Path.of("images", "spritesheets", "closescroll.png"), width * 6, height
            );
        }
    }

    /**
     * calculates the current frame of the animation.
     * @return the current frame of the animation.
     */
    private BufferedImage getFrame() {
        BufferedImage subImage = toBufferedImage(image);
        return subImage.getSubimage(frame * width, 0, width, height);
    }

    /**
     * getter for the bonds of the scroll.
     * @return the bounds of the scroll.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * getter for the timer.
     * @return the timer.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * draws the scroll on the screen.
     * @param g the graphics component.
     */
    public void draw(Graphics g) {
        g.drawImage(getFrame(), 1500, 10, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame++;
        if(frame == maxFrames) {
            frame = 0;
            setOpen(!open);
            timer.stop();
        }
        notifyObservers();
    }

    @Override
    public void addObserver(OpenScrollObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OpenScrollObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(OpenScrollObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * creates a log view for the scroll.
     * @param log the log to be converted.
     * @return the log view in the form of a text field.
     */
    private JTextArea createLog(String log) {
        JTextArea label = new JTextArea(log);
        label.setWrapStyleWord(true);
        label.setLineWrap(true);
        label.setEditable(false);
        label.setFocusable(false);
        label.setOpaque(false);
        label.setFont(createCustomFont());
        label.setForeground(Color.BLACK);
        return label;
    }

    /**
     * getter for the scroll pane.
     * @return the current scroll pane.
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public void onAddLog(Scroll scroll) {
        String lastLog = scroll.getLogs().get(scroll.getLogs().size() - 1);
        log.add(createLog(lastLog));
        notifyObservers();
    }
}
