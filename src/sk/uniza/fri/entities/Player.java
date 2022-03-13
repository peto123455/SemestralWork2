package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.*;

import java.awt.image.BufferedImage;

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

    public HealthSystem getHealthSystem() {
        return this.healthSystem;
    }

    public int getHearths() {
        return this.healthSystem.getHearts();
    }
}
