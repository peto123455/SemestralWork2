package sk.uniza.fri.main;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Particle;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.input.KeyHandler;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.map.Portal;
import sk.uniza.fri.map.PortalGroup;
import sk.uniza.fri.ui.GamePanel;
import sk.uniza.fri.ui.MessageBox;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game {
    private final GamePanel panel;

    private MapHandler mapHandler;
    private Player player;
    private final KeyHandler keyHandler;
    private final GameThread gameThread;

    public Game(GamePanel panel) {
        this.panel = panel;
        this.panel.setGame(this);

        this.initGame();
        this.keyHandler = new KeyHandler(this);
        this.gameThread = new GameThread(this);

        this.createKeyListener();
        this.createMouseListener();

    }

    public void startGame() {
        this.gameThread.start();
    }

    public void initGame() {
        this.player = new Player(this);
        this.mapHandler = new MapHandler(this);

        this.player.getPosition().setPosition(new Position(200, 520));
    }

    public void initGame(boolean died) {
        if (died) {
            this.panel.repaint();
            JOptionPane.showMessageDialog(null, "You died");
        }
        this.initGame();
        this.keyHandler.resetKeys();
    }

    public MapHandler getMapHandler() {
        return this.mapHandler;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void updateGame() {
        this.player.handleKeys(this.keyHandler.getPressedKeys(), this.mapHandler.getMap());
        this.checkForItems();
        this.handleEnemies();
        this.updateParticles();
        this.checkMessages();
        this.panel.repaint();
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
    }

    public void useHealthPotion() {
        this.getPlayer().getInventory().useItem(new ItemStack(EItemList.HEALTH_POTION, 1), this);
    }

    public void switchInventory() {
        this.panel.switchInventory();
    }

    private void attack(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.attack();
        }
    }

    public void attack() {
        this.player.hit(this.mapHandler.getEnemies());
    }

    private void createMouseListener() {
        this.panel.createMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Game.this.attack(e);
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

    private void updateParticles() {
        ArrayList<Particle> particles = Particle.getParticles();
        for (int i = 0; i < particles.size(); ++i) {
            particles.get(i).update();
        }
    }

    private void checkMessages() {
        for (int i = 0; i < MessageBox.getMessageBoxes().size(); ++i) {
            MessageBox.getMessageBoxes().get(i).update();
        }
    }

    public void onDeath() {
        this.initGame(true);
    }
}
