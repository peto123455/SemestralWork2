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

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int TILE_SIZE = 16 * 3;

    private Timer timer = new Timer();

    private Map map;
    private Player player;
    private KeyHandler keyHandler;

    public GamePanel() {
        this.map = new Map(this.WIDTH / 3, this.HEIGHT / 3);
        this.player = new Player();
        this.keyHandler = new KeyHandler(this);

        super.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        super.setDoubleBuffered(true);
        super.setBackground(Color.BLACK);

        this.map.setTile(1, 0, EImageList.WALL1);
        this.map.setTile(2, 0, EImageList.WALL1);
        this.map.setTile(3, 0, EImageList.WALL1);

        this.player.getPosition().setPosition(new Position(100, 100));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GamePanel.this.updateGame();
            }
        }, 50, 25);
    }

    public void updateGame() {
        for (Character c : this.keyHandler.getPressedKeys()) {
            this.player.getPosition().addPosition(Direction.getPosByChar(c));
        }
        this.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        for (int i = 0; i < this.map.getSizeY() ; ++i) {
            for (int j = 0; j < this.map.getSizeX(); ++j) {
                if (this.map.getTile(j, i) == null) {
                    continue;
                }

                g2d.drawImage(this.map.getTile(j, i).getImage(), j * GamePanel.TILE_SIZE, i * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

            }
        }

        Position position = this.player.getPosition();
        g2d.drawImage(this.player.getImage(), position.getCoordX() - GamePanel.TILE_SIZE / 2, position.getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2d.dispose();
    }

    public KeyHandler getKeyHandler() {
        return this.keyHandler;
    }
}
