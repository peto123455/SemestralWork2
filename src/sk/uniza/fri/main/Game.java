package sk.uniza.fri.main;

import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Particle;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.entities.Projectile;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.input.KeyHandler;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.ui.GamePanel;
import sk.uniza.fri.ui.MessageBox;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    private final GamePanel panel;

    private MapHandler mapHandler;
    private Player player;
    private final KeyHandler keyHandler;

    private boolean isFinished;

    public Game(GamePanel panel) {
        this.isFinished = false;
        this.panel = panel;
        this.panel.setGame(this);

        this.keyHandler = new KeyHandler(this);

        this.createKeyListener();
        this.createMouseListener();
    }

    public void startGame() {
        this.initGame();

        GameThread.getInstance().setGame(this);
        GameThread.getInstance().start();
    }

    public void initGame() {
        this.player = new Player(this);
        this.mapHandler = new MapHandler(this);

        this.player.getPosition().setPosition(new Position(200, 520));
    }

    public void finishGame() {
        this.isFinished = true;
    }

    public void initGame(boolean died) {
        if (died) {
            this.panel.repaint();
            JOptionPane.showMessageDialog(null, "You died");
            Projectile.resetProjectiles();
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
        if (!this.isFinished) {
            this.player.handleKeys(this.keyHandler.getPressedKeys(), this.mapHandler.getMap());
            this.mapHandler.checkForItems(this.player);
            this.handleUpdate();
            Particle.updateParticles();
            Projectile.updateProjectiles(this);
        }
        MessageBox.checkMessages();
        this.panel.repaint();
    }

    private void createKeyListener() {
        this.panel.createKeyListener(this.keyHandler);
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
        if (this.isFinished) {
            return;
        }
        this.player.hit(this.mapHandler.getEnemies());
    }

    public void useAction() {
        this.mapHandler.action(this.player);
    }

    private void createMouseListener() {
        this.panel.createMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Game.this.attack(e);
            }
        });
    }

    private void handleUpdate() {
        for (Entity entity : this.getMapHandler().getMap().getEntityList()) {
            entity.update(this);
        }
    }

    public void onDeath() {
        this.initGame(true);
    }

    public boolean isFinished() {
        return this.isFinished;
    }
}
