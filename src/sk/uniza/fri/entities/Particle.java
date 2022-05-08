package sk.uniza.fri.entities;


import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Particle extends Entity {
    private int frameDuration;
    private int currentFrame;
    private int stage;

    private static ArrayList<Particle> particles = new ArrayList<>();

    /**
     * Vytvorí efekt
     * @param position Pozícia efektu
     * @param images Obrázky efektu (Animácia)
     * @param frameDuration Dĺžka jednej snímky
     */
    public Particle(Position position, EImageList[] images, int frameDuration) {
        super(images, ERenderLayer.PARTICLES);
        this.getPosition().setPosition(position);
        this.frameDuration = frameDuration;
        this.stage = 0;
        this.currentFrame = 0;
        Particle.particles.add(this);
    }

    /**
     * Stará sa o aktualizovanie snímky
     */
    public void update() {
        ++this.currentFrame;
        if (this.frameDuration > this.currentFrame) {
            return;
        } else if (this.stage < super.getImages().length - 1) {
            ++this.stage;
            this.currentFrame = 0;
            return;
        }
        Particle.particles.remove(this);
    }

    /**
     * @return Vráti aktuálnu smínku
     */
    @Override
    public BufferedImage getImage() {
        return super.getImage(this.stage);
    }

    /**
     * Stará sa o vykresľovanie všetkých efektov
     * @param g2d Plátno
     */
    public static void drawParticles(Graphics2D g2d) {
        for (Particle particle : Particle.particles) {
            particle.draw(g2d);
        }
    }

    /**
     * Stará sa o aktualizáciu všetkých efektov
     */
    public static void updateParticles() {
        for (int i = 0; i < Particle.particles.size(); ++i) {
            Particle.particles.get(i).update();
        }
    }
}
