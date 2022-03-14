package sk.uniza.fri.main;

import sk.uniza.fri.essentials.ETileList;
import sk.uniza.fri.ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
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
