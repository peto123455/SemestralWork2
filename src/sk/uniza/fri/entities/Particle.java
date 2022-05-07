package sk.uniza.fri.entities;


import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Particle extends Entity {
    private int frameDuration;
    private int currentFrame;
    private int stage;

    private static ArrayList<Particle> particles = new ArrayList<>();

    public Particle(Position position, EImageList[] images, int frameDuration) {
        super(images);
        this.getPosition().setPosition(position);
        this.frameDuration = frameDuration;
        this.stage = 0;
        this.currentFrame = 0;
        Particle.particles.add(this);
    }

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

    public static void updateParticles() {
        for (int i = 0; i < Particle.particles.size(); ++i) {
            Particle.particles.get(i).update();
        }
    }
}
