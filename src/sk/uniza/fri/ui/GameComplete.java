package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EFontList;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class GameComplete {

    /**
     * Vykresluje okno konca hry
     * @param g2d Plátno
     */
    public static void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(GamePanel.WIDTH / 2 - 250, GamePanel.HEIGHT / 2 - 50, 500, 110, 10, 10);

        g2d.setColor(Color.BLACK);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(GamePanel.WIDTH / 2 - 250, GamePanel.HEIGHT / 2 - 50, 500, 110, 10, 10);
        g2d.setStroke(tmp);
        g2d.setFont(EFontList.RPG.getFont().deriveFont(64f));
        g2d.drawString("Game Complete !", GamePanel.WIDTH / 2 - 215, GamePanel.HEIGHT / 2);
        g2d.setFont(EFontList.RPG.getFont().deriveFont(25f));
        g2d.drawString("You've successfully finished the game !", GamePanel.WIDTH / 2 - 220, GamePanel.HEIGHT / 2 + 40);
    }
}
