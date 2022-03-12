package sk.uniza.fri;

import java.awt.image.BufferedImage;

public class Player extends Entity {
    private Direction direction;

    public Player() {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.direction = Direction.Right;
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
}
