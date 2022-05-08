package sk.uniza.fri.main;

import sk.uniza.fri.enums.ETileList;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class GameTile {

    private BufferedImage image;
    private boolean hasCollision;

    /**
     * Blok
     * @param tile Typ bloku
     */
    public GameTile(ETileList tile) {
        this.image = tile.getImage();
        this.hasCollision = tile.hasCollision();
    }

    /**
     * @return Obrázok bloku
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * @return Má kolízie
     */
    public boolean hasCollision() {
        return this.hasCollision;
    }

    /**
     * Stará sa o vykreslovanie bloku
     * @param g2d Plátno
     * @param i Súradnica X
     * @param j Súradnica Y
     */
    public void draw(Graphics2D g2d, int i, int j) {
        g2d.drawImage(this.getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
