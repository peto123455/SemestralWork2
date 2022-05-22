package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EFontList;
import sk.uniza.fri.inventory.Inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

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
        g2d.fillRoundRect(GamePanel.WIDTH / 4, GamePanel.HEIGHT / 8, GamePanel.WIDTH / 2, (int)((double)GamePanel.HEIGHT / 1.33), 10, 10);

        g2d.setColor(Color.BLACK);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(GamePanel.WIDTH / 4, GamePanel.HEIGHT / 8, GamePanel.WIDTH / 2, (int)((double)GamePanel.HEIGHT / 1.33), 10, 10);
        g2d.setStroke(tmp);
        g2d.setFont(EFontList.RPG.getFont());
        g2d.drawString("Inventory", GamePanel.WIDTH / 2 - 72, GamePanel.HEIGHT / 8 + 30);
        g2d.setFont(EFontList.RPG.getFont().deriveFont(25f));

        //Rozdelenie riadkov
        int y = GamePanel.HEIGHT / 8 + 50;
        for (String text : inventory.toString().split("\n")) {
            y += g2d.getFontMetrics().getHeight();
            g2d.drawString(text, GamePanel.WIDTH / 4 + 50 - 12, y);
        }
    }
}
