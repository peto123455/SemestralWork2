package sk.uniza.fri.main;

import sk.uniza.fri.enums.ETileList;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class GameTile {

    private BufferedImage image;
    private boolean hasCollision;

    public GameTile(ETileList tile) {
        this.image = tile.getImage();
        this.hasCollision = tile.hasCollision();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean hasCollision() {
        return this.hasCollision;
    }

    public void draw(Graphics2D g2d, int i, int j) {
        g2d.drawImage(this.getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
