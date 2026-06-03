package nl.rug.oop.rts.view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sound playing class.
 */
public class Sounds {
    /**
     * all concurrent clips.
     */
    private final Map<String, Clip> clips = new ConcurrentHashMap<>();

    /**
     * function to play a sound whenever an action happens.
     * @param soundname string of the file name.
     */
    public void playSound(String soundname){
        File file = new File("src/main/resources/sounds/"+soundname);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clips.put(soundname, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function to stop the sound from playing.
     * @param soundname the name of the file it needs to stop.
     */
    public void stopSound(String soundname) {
        Clip clip = clips.get(soundname);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
            clips.remove(soundname);
        }
    }

}
