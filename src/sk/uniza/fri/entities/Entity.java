package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    private BufferedImage[] images;

    private Position position;

    public Entity(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }

        this.position = new Position();
    }

    public BufferedImage getImage() {
        return this.getImage(0);
    }

    public BufferedImage getImage(int i) {
        if (i < 0 || i >= this.images.length) {
            return null;
        }

        return this.images[i];
    }

    public Position getPosition() {
        return this.position;
    }

    public Position getPositionRelativeToGrid() {
        return Position.getPositionRelativeToGrid(this.position);
    }

    public boolean isNearEntity(Entity entity, double distance) {
        return Position.getDistance(this.getPosition(), entity.getPosition()) <= distance;
    }

    public void changeImages(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }
    }

    protected BufferedImage[] getImages() {
        return this.images;
    }
}
