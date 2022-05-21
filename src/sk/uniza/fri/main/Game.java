package sk.uniza.fri.main;

import entities.Entity;
import entities.Particle;
import entities.Player;
import entities.Projectile;
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

    /**
     * Hlavná inštancia hry, jej logika
     * @param panel
     */
    public Game(GamePanel panel) {
        this.isFinished = false;
        this.panel = panel;
        this.panel.setGame(this);

        this.keyHandler = new KeyHandler(this);

        this.panel.createKeyListener(this.keyHandler);
        this.createMouseListener();
    }

    /**
     * Začne hru
     */
    public void startGame() {
        this.initGame();

        GameThread.getInstance().setGame(this);
        GameThread.getInstance().start();
    }

    /**
     * Stará sa o načítanie hry
     */
    public void initGame() {
        this.player = new Player(this);
        this.mapHandler = new MapHandler(this);

        this.player.getPosition().setPosition(new Position(200, 520));
    }

    /**
     * Ukončí hru
     */
    public void finishGame() {
        this.isFinished = true;
        if (this.panel.isInventoryVisible()) {
            this.switchInventory();
        }
    }

    /**
     * Stará sa o načítanie hry, používa sa ak hráč zomrel
     * @param died
     */
    public void initGame(boolean died) {
        if (died) {
            this.panel.repaint();
            JOptionPane.showMessageDialog(null, "You died");
            Projectile.resetProjectiles();
        }
        this.initGame();
        this.keyHandler.resetKeys();
    }

    /**
     * @return Vráti Map handler
     */
    public MapHandler getMapHandler() {
        return this.mapHandler;
    }

    /**
     * @return Vráti hráča
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Stará sa o aktualizáciu hry
     */
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

    /**
     * Použije 1 elixír života
     */
    public void useHealthPotion() {
        this.getPlayer().getInventory().useItem(new ItemStack(EItemList.HEALTH_POTION, 1), this);
    }

    /**
     * Použije 1 elixír rýchlosti
     */
    public void useSpeedPotion() {
        this.getPlayer().getInventory().useItem(new ItemStack(EItemList.SPEED_POTION, 1), this);
    }

    /**
     * Zobrazí / Skryje inventár
     */
    public void switchInventory() {
        this.panel.switchInventory();
    }

    /**
     * Hráč zaútočí
     */
    public void attack() {
        if (this.isFinished) {
            return;
        }
        this.player.hit(this.mapHandler.getEnemies());
    }

    /**
     * Hráč vykoná akciu
     */
    public void useAction() {
        this.mapHandler.action(this.player);
    }

    /**
     * Volá sa pri smrti hráča
     */
    public void onDeath() {
        this.initGame(true);
    }

    /**
     * @return Či je hra ukončená
     */
    public boolean isFinished() {
        return this.isFinished;
    }

    //Private

    private void attack(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.attack();
        }
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
}
