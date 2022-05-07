package sk.uniza.fri.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameComplete {

    /**
     * Vykresluje okno konca hry
     * @param g2d
     */
    public static void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(GamePanel.WIDTH / 4, GamePanel.HEIGHT / 8, GamePanel.WIDTH / 2, (int)((double)GamePanel.HEIGHT / 1.33));
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 64));
        g2d.drawString("Game Complete", GamePanel.WIDTH / 4 + GamePanel.WIDTH / 16, GamePanel.HEIGHT / 8 + 80);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString("You've successfully finished the game !", GamePanel.WIDTH / 4 + GamePanel.WIDTH / 8, GamePanel.HEIGHT / 8 + 120);
    }
}
