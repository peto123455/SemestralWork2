package sk.uniza.fri.ui;

import sk.uniza.fri.essentials.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class InventoryRenderer {
    private boolean isVisible;

    /**
     * Slúži na vykresľovanie inventára hráča
     */
    public InventoryRenderer() {
        this.isVisible = false;
    }

    /**
     * Zistí či je inventár zobrazený, alebo nie.
     * @return Či sa okno práve zobrazuje
     */
    public boolean isVisible() {
        return this.isVisible;
    }

    /**
     * Vypne / Zapne zobrazenie inventára
     */
    public void switchVisible() {
        this.isVisible = !this.isVisible;
    }

    /**
     * Vykresľuje samotný inventár
     * @param g2d Plátno
     * @param inventory Inventár hráča
     */
    public void draw(Graphics2D g2d, Inventory inventory) {
        if (!this.isVisible) {
            return;
        }

        g2d.setColor(Color.WHITE);
        g2d.fillRect(GamePanel.WIDTH / 4, GamePanel.HEIGHT / 8, GamePanel.WIDTH / 2, (int)((double)GamePanel.HEIGHT / 1.33));
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        g2d.drawString("Inventory", GamePanel.WIDTH / 4 + GamePanel.WIDTH / 4 - 53, GamePanel.HEIGHT / 8 + 30);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));

        //Rozdelenie riadkov
        int y = GamePanel.HEIGHT / 8 + 50;
        for (String text : inventory.toString().split("\n")) {
            y += g2d.getFontMetrics().getHeight();
            g2d.drawString(text, GamePanel.WIDTH / 4 + 50 - 12, y);
        }
    }
}
