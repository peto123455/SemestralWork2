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
        new ItemCoins(100, 300);

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

            GameTile tile = this.mapHandler.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
            if (tile != null && tile.hasCollision()) {
                continue;
            }

            //Player Movement
            Direction direction = Direction.getDirByChar(c);
            if (direction == Direction.Right || direction == Direction.LEFT) {
                this.player.setDirection(direction);
            }
            this.player.getPosition().addPosition(direction.getPosByChar(c, 4));

            this.checkForItems();
        }
        panel.repaint();
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

    private void checkForItems() {
        for (int i = 0; i < Item.getSpawnedItems().size(); ++i) {
            if (this.player.isNearEntity(Item.getSpawnedItems().get(i), 20)) {
                Item.getSpawnedItems().remove(i).pickup();
            }
        }
    }
}
