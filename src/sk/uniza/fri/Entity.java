package sk.uniza.fri;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private final BufferedImage image;

    private Position position;

    public Entity(EImageList image) {
        this.image = image.getImage();
        this.position = new Position();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Position getPosition() {
        return this.position;
    }
}
