package sk.uniza.fri.main;

import sk.uniza.fri.entities.*;
import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.map.Portal;
import sk.uniza.fri.map.PortalGroup;
import sk.uniza.fri.ui.GamePanel;

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

        Enemy.createEnemies(mapHandler, player);
        PortalGroup.createPortals(this.mapHandler);
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

        Position finalPosition = new Position();

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
            finalPosition.addPosition(direction.getPosByChar(c, 4));

            this.checkForItems();
        }

        /*if (finalPosition.getCoordX() != 0 && finalPosition.getCoordY() != 0) {
            finalPosition = new Position(finalPosition.getCoordX() / 2, finalPosition.getCoordY() / 2);
        }*/

        this.player.getPosition().addPosition(finalPosition);

        this.handleEnemies();

        this.updateParticles();

        panel.repaint();
    }

    private void updateParticles() {
        ArrayList<Particle> particles = Particle.getParticles();
        for (int i = 0; i < particles.size(); ++i) {
            particles.get(i).update();
        }
    }

    private void createKeyListener() {
        this.panel.createKeyListener(this.keyHandler);
    }

    public void enterPortal() {
        for (Portal portal : this.getMapHandler().getPortals()) {
            if (Position.getDistance(portal.getPosition(), this.player.getPosition()) < 80) {
                portal.teleport(this.player, this.mapHandler);
                return;
            }
        }
        return;
    }

    public void useHealthPotion() {
        this.getPlayer().getInventory().useItem(new ItemStack(EItemList.HEALTH_POTION, 1), this);
    }

    public void switchInventory() {
        this.panel.switchInventory();
    }

    private void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.player.hit(this.mapHandler.getEnemies());
        }
    }

    private void createMouseListener() {
        this.panel.createMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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
