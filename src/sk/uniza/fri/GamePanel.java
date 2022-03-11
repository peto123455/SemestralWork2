package sk.uniza.fri;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class GamePanel extends JPanel {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int TILE_SIZE = 16 * 3;

    private Game game;

    public GamePanel() {
        super.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        super.setDoubleBuffered(true);
        super.setBackground(Color.BLACK);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        for (int i = 0; i < this.game.getMap().getSizeY() ; ++i) {
            for (int j = 0; j < this.game.getMap().getSizeX(); ++j) {
                if (this.game.getMap().getTile(j, i) == null) {
                    continue;
                }

                g2d.drawImage(this.game.getMap().getTile(j, i).getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

            }
        }

        Position position = this.game.getPlayer().getPosition();
        g2d.drawImage(this.game.getPlayer().getImage(), position.getCoordX() - GamePanel.TILE_SIZE / 2, position.getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2d.dispose();
    }

}
