package sk.uniza.fri.main;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.ItemCoins;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.ui.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        new ItemCoins(100, 300, 50);

        this.createKeyListener();
        this.createMouseListener();
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
        this.getMapHandler().getEnemies().get(0).goToPos(this.player.getPosition());

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
            if (direction == Direction.RIGHT || direction == Direction.LEFT) {
                this.player.setDirection(direction);
            }
            this.player.getPosition().addPosition(direction.getPosByChar(c, 4));

            this.checkForItems();
        }

        this.handleEnemies();

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

    private void mouseClicked(MouseEvent e) {
        this.player.hit(this.mapHandler.getEnemies());
    }

    private void createMouseListener() {
        this.panel.createMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.this.mouseClicked(e);
            }
        });
    }

    private void checkForItems() {
        ArrayList<Item> items = this.mapHandler.getItems();
        for (int i = 0; i < items.size(); ++i) {
            if (this.player.isNearEntity(items.get(i), 30)) {
                this.player.getInventory().addItemStack(items.remove(i).pickup());
            }
        }
    }

    private void handleEnemies() {
        ArrayList<Enemy> enemies = this.getMapHandler().getEnemies();
        for (Enemy enemy : enemies) {
            enemy.update(this);
        }
    }
}
