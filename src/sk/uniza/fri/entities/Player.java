package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.HealthSystem;
import sk.uniza.fri.essentials.IEntityAlive;
import sk.uniza.fri.essentials.Inventory;
import sk.uniza.fri.entities.Enemy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity implements IEntityAlive {
    private Direction direction;
    private Inventory inventory;
    private HealthSystem healthSystem;

    public Player() {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.direction = Direction.RIGHT;
        this.inventory = new Inventory();
        this.healthSystem = new HealthSystem(5);
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.RIGHT) {
            return super.getImage(0);
        }
        return super.getImage(1);
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
        return this.healthSystem.takeHeart();
    }

    public int getHearths() {
        return this.healthSystem.getHearts();
    }

    public void hit(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (super.isNearEntity(enemy, 40)) {
                enemy.takeHeart();
            }
        }
    }
}
