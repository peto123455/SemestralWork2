package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.ESoundList;
import sk.uniza.fri.essentials.HealthSystem;
import sk.uniza.fri.essentials.IEntityAlive;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Inventory;
import sk.uniza.fri.main.Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity implements IEntityAlive {
    private Direction direction;
    private Inventory inventory;
    private HealthSystem healthSystem;
    private Game game;

    public Player(Game game) {
        super(new EImageList[] {EImageList.PLAYER});

        this.direction = Direction.RIGHT;
        this.inventory = new Inventory();
        this.healthSystem = new HealthSystem(5);
        this.game = game;
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.LEFT) {
            return ImageTools.flip(super.getImage());
        }
        return super.getImage();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
            return false;
        }
        return true;
    }

    public int getHearths() {
        return this.healthSystem.getHearts();
    }

    public void hit(ArrayList<Enemy> enemies) {
        ESoundList.playSound(ESoundList.SWORD_SLASH);
        new ParticleSlash(this.getPosition(), this.direction);

        for (Enemy enemy : enemies) {
            if (super.isNearEntity(enemy, 50)) {
                enemy.takeHeart();
            }
        }
    }

    private void onDeath() {
        this.game.onDeath();
    }

    public boolean isMaxHearts() {
        return this.healthSystem.isHaxHearts();
    }

    public void addHeart() {
        this.healthSystem.addHeart();
    }
}
