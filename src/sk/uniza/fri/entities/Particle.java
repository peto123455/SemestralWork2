package sk.uniza.fri.entities;


import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Particle extends Entity {
    private long nextUpdate;
    private int frameDuration;
    private int stage;

    private static ArrayList<Particle> particles = new ArrayList<>();

    public Particle(Position position, EImageList[] images, int frameDuration) {
        super(images);
        this.getPosition().setPosition(position);
        this.nextUpdate = System.currentTimeMillis() + this.frameDuration;
        this.frameDuration = frameDuration;
        this.stage = 0;
        Particle.particles.add(this);
    }

    public void update() {
        if (this.nextUpdate > System.currentTimeMillis()) {
            return;
        }
        if (this.stage < super.getImages().length - 1) {
            ++this.stage;
            this.nextUpdate = System.currentTimeMillis() + this.frameDuration;
            return;
        }
        Particle.particles.remove(this);
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage(this.stage);
    }

    public static ArrayList<Particle> getParticles() {
        return particles;
    }

    public static void drawParticles(Graphics2D g2d) {
        for (Particle particle : Particle.getParticles()) {
            particle.draw(g2d);
        }
    }
}
