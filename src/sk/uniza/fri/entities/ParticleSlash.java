package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;

public class ParticleSlash extends Particle {
    private Direction direction;

    public ParticleSlash(Position position, Direction direction) {
        super(position, new EImageList[]{EImageList.SLASH1, EImageList.SLASH2, EImageList.SLASH3}, 3);
        super.getPosition().addPosition(direction.getPos(20));
        this.direction = direction;
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.LEFT) {
            return ImageTools.flip(super.getImage());
        }

        return super.getImage();
    }
}
