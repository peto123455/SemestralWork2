package sk.uniza.fri.ui;

import sk.uniza.fri.essentials.Inventory;

import java.awt.*;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class InventoryRenderer {
    private boolean isVisible;

    public InventoryRenderer() {
        this.isVisible = false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void switchVisible() {
        this.isVisible = !this.isVisible;
    }

    public void draw(Graphics2D g2d, Inventory inventory) {
        if (!this.isVisible) {
            return;
        }

        g2d.setColor(Color.WHITE);
        g2d.fillRect(GamePanel.WIDTH / 4, GamePanel.HEIGHT / 8, GamePanel.WIDTH / 2, (int)((double) GamePanel.HEIGHT / 1.33));
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString(inventory.toString(), GamePanel.WIDTH / 4 + 50 - 12, GamePanel.HEIGHT / 8 + 50);
    }
}
