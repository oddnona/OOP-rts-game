package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.view.regionViews.NodeView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * class that draws the army on the panel.
 */
public class ArmyView{
    /**
     * the image of the army.
     */
    private Image image;
    /**
     * the army to draw.
     */
    private Army army;
    /**
     * information about the animation.
     */
    private int frameWidth, frameHeight;

    /**
     * constructor for the army view.
     * @param army the army model.
     */
    public ArmyView(Army army) {
        this.army = army;
        frameWidth = 150;
        frameHeight = 150;
        image = TextureLoader.getInstance().getTexture(
                Path.of("images", "spritesheets", "manyswords.png"), frameWidth * army.getMaxFrames(), frameHeight
        );
    }

    /**
     * draws the army on the edge.
     * @param g the graphics object to draw on screen.
     * @param edgeView the edge view the army is placed.
     */
    public void draw(Graphics g, EdgeView edgeView) {
        if (!army.isMoving()) {
            Point point = edgeView.getEdge().getMiddle();
            g.drawImage(getFrame(), point.x - (frameWidth / 2), point.y - (frameHeight / 2), null);
        } else {
            Point start = army.getPreviousNode().getPosition();
            Point end = edgeView.getEdge().getMiddle();
            int length = getLength(start, end);
            if (length == 0) {
                return;
            }
            double dx = (double)(end.x - start.x) / length;
            double dy = (double)(end.y - start.y) / length;
            int x = (int)(start.x + army.getPositionOnTheEdge() * dx);
            int y = (int)(start.y + army.getPositionOnTheEdge() * dy);
            g.drawImage(getFrame(), x - (frameWidth / 2), y - (frameHeight / 2), null);
        }
    }

    /**
     * draws the army on the node.
     * @param g the graphics object to draw the army.
     * @param nodeView the node view the army belongs to.
     */
    public void draw(Graphics g, NodeView nodeView) {
        Point start, end;
        if (!army.isMoving()) {
            start = nodeView.getNode().getPosition();
            g.drawImage(getFrame(), start.x - (frameWidth / 2), start.y - (frameHeight / 2), null);
        } else {
            start = army.getPreviousEdge().getMiddle();
            end = nodeView.getNode().getPosition();
            int length = getLength(start, end);
            if (length == 0) {
                return;
            }
            double dx = (double)(end.x - start.x) / length;
            double dy = (double)(end.y - start.y) / length;
            int x = (int)(start.x + army.getPositionOnTheEdge() * dx);
            int y = (int)(start.y + army.getPositionOnTheEdge() * dy);
            g.drawImage(getFrame(), x - (frameWidth / 2), y - (frameHeight / 2), null);
        }

    }

    /**
     * calculates the distance between two points.
     * @param start the fist point.
     * @param end the second point.
     * @return the distance.
     */
    private int getLength(Point start, Point end) {
        int x = start.x - end.x;
        int y = start.y - end.y;
        return (int)Math.sqrt((x * x) + (y * y));
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
     * calculates the current frame of the animation.
     * @return the current frame of the animation.
     */
    private BufferedImage getFrame() {
        BufferedImage subImage = toBufferedImage(image);
        return subImage.getSubimage(army.getFrames() * frameWidth, 0, frameWidth, frameHeight);
    }
}
