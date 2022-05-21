package sk.uniza.fri.enums;

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
    POTION_DRINK("/sounds/potion.wav"),
    PORTAL("/sounds/portal.wav"),
    CHEST("/sounds/chest.wav"),
    MUSIC("/sounds/music.wav"),
    MESSAGE("/sounds/message.wav"),
    MESSAGE_WRONG("/sounds/error.wav"),
    PROJECTILE_MAGIC("/sounds/projectileMagic.wav"),
    QUEST_COMPLETE("/sounds/questComplete.wav"),
    SWORD_STAB("/sounds/swordStab.wav");

    private static final double VOLUME = 0.3; // Ovládanie hlasitosti 0 - 1
    private static final double VOLUME_LOOP = 0.1; // Ovládanie hlasitosti loopov 0 - 1

    private final String route;

    /**
     * Zoznam zvukov
     * @param route Cesta k zvuku
     */
    ESoundList(String route) {
        this.route = route;
    }

    /**
     * @return Vráti cestu k zvuku
     */
    public String getRoute() {
        return this.route;
    }

    /**
     * Prehrá zvuk
     * @param sound Zvuk
     */
    public static void playSound(ESoundList sound) {
        ESoundList.playSound(sound, false);
    }

    /**
     * Prehrá zvuk
     * @param sound Zvuk
     * @param loop Opakovanie
     */
    public static void playSound(ESoundList sound, boolean loop) {
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

                    //Prehratie zvuku
                    if (loop) {
                        fc.setValue(20f * (float)Math.log10(ESoundList.VOLUME_LOOP));
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } else {
                        fc.setValue(20f * (float)Math.log10(ESoundList.VOLUME));
                        clip.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //Private

    /**
     * @return Vríti Audo Input Stream zvuku
     */
    private AudioInputStream getAudioInputStream() {
        try {
            return AudioSystem.getAudioInputStream(this.getClass().getResource(this.getRoute()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return null;
    }
}
