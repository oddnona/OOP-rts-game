package nl.rug.oop.rts;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.controller.GameController;
import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.view.Frame;

/**
 * Main class of the application. Add more details here.
 */
public class Main {

    /**
     * Main function. Add more details here.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        Game game = new Game();
        Frame frame = new Frame(game);
        GameController gameController = new GameController(game, frame);
    }
}