package sk.uniza.fri;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer timer = new Timer();
    private GamePanel panel;

    private MapHandler mapHandler;
    private Player player;
    private KeyHandler keyHandler;

    public Game(GamePanel panel) {
        this.panel = panel;
        this.panel.setGame(this);

        this.mapHandler = new MapHandler();
        this.player = new Player();
        this.keyHandler = new KeyHandler(this);

        this.player.getPosition().setPosition(new Position(100, 100));

        this.createKeyListener();
    }

    public void startGame() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Game.this.updateGame();
            }
        }, 50, 20);
    }

    public MapHandler getMapHandler() {
        return this.mapHandler;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void updateGame() {
        Position pos = this.player.getPositionRelativeToGrid();

        for (Character c : this.keyHandler.getPressedKeys()) {
            //Collision System
            Position futurePosition = Direction.getPosByChar(c, 16);
            futurePosition.addPosition(this.player.getPosition());
            futurePosition = Position.getPositionRelativeToGrid(futurePosition);

            if (this.mapHandler.getTile(futurePosition.getCoordX(), futurePosition.getCoordY()) != null) {
                continue;
            }

            //Player Movement
            this.player.getPosition().addPosition(Direction.getPosByChar(c, 4));
        }
        panel.repaint();
    }

    public KeyHandler getKeyHandler() {
        return this.keyHandler;
    }

    private void createKeyListener() {
        this.panel.createKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Game.this.keyHandler.keyUpdate(Character.toLowerCase(e.getKeyChar()), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Game.this.keyHandler.keyUpdate(Character.toLowerCase(e.getKeyChar()), false);
            }
        });
    }
}
