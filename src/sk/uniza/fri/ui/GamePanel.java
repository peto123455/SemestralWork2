package sk.uniza.fri.ui;

import sk.uniza.fri.entities.Particle;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.main.Game;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    public static final int WIDTH = 1280 - 32;
    public static final int HEIGHT = 720;
    public static final int TILE_SIZE = 16 * 3;

    private Game game;
    private InventoryRenderer inventoryRenderer;

    public GamePanel() {
        super.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        super.setDoubleBuffered(true);

        this.inventoryRenderer = new InventoryRenderer();
    }

    public void createKeyListener(KeyListener keyListener) {
        this.addKeyListener(keyListener);
    }

    public void createMouseListener(MouseListener mouseListener) {
        this.addMouseListener(mouseListener);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        /* Hra */
        this.game.getMapHandler().drawTiles(g2d);       //Políčka
        this.game.getMapHandler().drawEntities(g2d);    //Entity (Nepriatelia, predmety, bedne, portále)
        this.game.getPlayer().draw(g2d);                //Hráč
        Particle.drawParticles(g2d);                    //Efekty

        /* UI */
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 25));
        g2d.drawString(String.format("Coins: %d", this.game.getPlayer().getInventory().getItemAmount(EItemList.COINS)), 5, 35);
        this.drawHearts(1223, 24, this.game.getPlayer().getHearts(), g2d);
        this.inventoryRenderer.draw(g2d, this.game.getPlayer().getInventory());
        if (this.game.getPlayer().getQuestHandler().isQuest()) {
            this.game.getPlayer().getQuestHandler().draw(g2d);
        }

        for (MessageBox message : MessageBox.getMessageBoxes()) {
            message.draw(g2d);
        }

        g2d.dispose();
    }

    private void drawHearts(int x, int y, int amount, Graphics2D g2d) {
        for (int i = 0; i < amount; ++i) {
            g2d.drawImage(EImageList.HEART.getImage(), x -  39 / 2 - 50 * i, y - 36 / 2, 39, 36, null);
        }
    }

    public void switchInventory() {
        this.inventoryRenderer.switchVisible();
    }
}
