package sk.uniza.fri.ui;

import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.essentials.Position;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    public static final int WIDTH = 1280 - 32;
    public static final int HEIGHT = 720;
    public static final int TILE_SIZE = 16 * 3;

    private Game game;

    public GamePanel() {
        super.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        super.setDoubleBuffered(true);
    }

    public void createKeyListener(KeyListener keyListener) {
        this.addKeyListener(keyListener);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        //g2d.setColor(Color.BLACK);

        //Políčka
        for (int i = 0; i < this.game.getMapHandler().getSizeY() ; ++i) {
            for (int j = 0; j < this.game.getMapHandler().getSizeX(); ++j) {
                if (this.game.getMapHandler().getTile(j, i) == null) {
                    continue;
                }

                g2d.drawImage(this.game.getMapHandler().getTile(j, i).getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

            }
        }

        //Itemy
        for (Item item : Item.getSpawnedItems()) {
            g2d.drawImage(item.getImage(), item.getPosition().getCoordX() - GamePanel.TILE_SIZE / 2, item.getPosition().getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }

        //Hráč
        Position position = this.game.getPlayer().getPosition();
        g2d.drawImage(this.game.getPlayer().getImage(), position.getCoordX() - GamePanel.TILE_SIZE / 2, position.getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        //UI
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g2d.drawString(String.format("Coins: %d", this.game.getPlayer().getInventory().getItemAmount(EItemList.COINS)), 5, 30);

        g2d.dispose();
    }

}
