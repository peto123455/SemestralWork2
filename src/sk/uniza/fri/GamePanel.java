package sk.uniza.fri;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyListener;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
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

        for (int i = 0; i < this.game.getMapHandler().getSizeY() ; ++i) {
            for (int j = 0; j < this.game.getMapHandler().getSizeX(); ++j) {
                if (this.game.getMapHandler().getTile(j, i) == null) {
                    continue;
                }

                g2d.drawImage(this.game.getMapHandler().getTile(j, i).getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

            }
        }

        for (Item item : Item.getSpawnedItems()) {
            g2d.drawImage(item.getImage(), item.getPosition().getCoordX(), item.getPosition().getCoordY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }

        Position position = this.game.getPlayer().getPosition();
        g2d.drawImage(this.game.getPlayer().getImage(), position.getCoordX() - GamePanel.TILE_SIZE / 2, position.getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2d.dispose();
    }

}
