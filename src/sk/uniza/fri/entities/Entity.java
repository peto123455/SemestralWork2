package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.getImage(), this.getPosition().getCoordX() - GamePanel.TILE_SIZE / 2, this.getPosition().getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    protected BufferedImage[] getImages() {
        return this.images;
    }
}
