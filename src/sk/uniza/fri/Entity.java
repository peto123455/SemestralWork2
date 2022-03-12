package sk.uniza.fri;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private final BufferedImage[] images;

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
}
