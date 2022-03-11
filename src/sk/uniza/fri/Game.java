package sk.uniza.fri;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer timer = new Timer();
    private GamePanel panel;

    private Map map;
    private Player player;
    private KeyHandler keyHandler;

    public Game(GamePanel panel) {
        this.panel = panel;
        this.panel.setGame(this);

        this.map = new Map(GamePanel.WIDTH / 3, GamePanel.HEIGHT / 3);
        this.player = new Player();
        this.keyHandler = new KeyHandler(this);

        this.map.setTile(1, 0, EImageList.WALL1);
        this.map.setTile(2, 0, EImageList.WALL1);
        this.map.setTile(3, 0, EImageList.WALL1);

        this.player.getPosition().setPosition(new Position(100, 100));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Game.this.updateGame();
            }
        }, 50, 25);
    }

    public Map getMap() {
        return this.map;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void updateGame() {
        for (Character c : this.keyHandler.getPressedKeys()) {
            this.player.getPosition().addPosition(Direction.getPosByChar(c));
        }
        panel.repaint();
    }

    public KeyHandler getKeyHandler() {
        return this.keyHandler;
    }
}
