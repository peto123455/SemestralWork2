package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Inventory;

import java.awt.image.BufferedImage;

public class Player extends Entity {
    private Direction direction;
    private Inventory inventory;

    public Player() {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.direction = Direction.Right;
        this.inventory = new Inventory();
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.Right) {
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
}
