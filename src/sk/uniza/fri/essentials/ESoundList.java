package sk.uniza.fri.essentials;

import javax.sound.sampled.*;
import java.io.IOException;

public enum ESoundList {
    COIN_PICKUP("/sounds/coinPickup.wav"),
    DEATH("/sounds/death.wav"),
    SWORD_SLASH("/sounds/swordSlash.wav"),
    SOWRD_STAB("/sounds/swordStab.wav");

    String route;

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
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }.start();
    }
}
