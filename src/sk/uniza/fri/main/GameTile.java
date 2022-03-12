package sk.uniza.fri.main;

import sk.uniza.fri.essentials.ETileList;

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
}
