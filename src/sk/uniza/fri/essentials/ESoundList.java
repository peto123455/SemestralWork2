package sk.uniza.fri.essentials;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public enum ESoundList {
    COIN_PICKUP("/sounds/coinPickup.wav"),
    DEATH("/sounds/death.wav"),
    SWORD_SLASH("/sounds/swordSlash.wav"),
    PICKUP("/sounds/pickup.wav"),
    SOWRD_STAB("/sounds/swordStab.wav");

    private static final double VOLUME = 0.3; // Ovládanie hlasitosti 0 - 1

    private String route;

    ESoundList(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }

    private AudioInputStream getAudioInputStream() {
        try {
            return AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(this.getRoute()));
        } catch (IOException e) {
            //Nič
        } catch (UnsupportedAudioFileException e) {
            //Nič
        }
        return null;
    }

    public static void playSound(ESoundList sound) {
        new Thread() {
            public void run() {
                try {
                    Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                            this.interrupt();
                        }
                    });
                    clip.open(sound.getAudioInputStream());

                    //Nastavenie hlasitosti
                    FloatControl fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                    fc.setValue(20f * (float)Math.log10(ESoundList.VOLUME));

                    //Prehratie zvuku
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }.start();
    }
}
