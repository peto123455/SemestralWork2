package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;

public class ParticleSlash extends Particle {
    private EDirection EDirection;

    public ParticleSlash(Position position, EDirection EDirection) {
        super(position, new EImageList[]{EImageList.SLASH1, EImageList.SLASH2, EImageList.SLASH3}, 3);
        super.getPosition().addPosition(EDirection.getPos(20));
        this.EDirection = EDirection;
    }

    @Override
    public BufferedImage getImage() {
        if (this.EDirection == EDirection.LEFT) {
            return ImageTools.flip(super.getImage());
        }

        return super.getImage();
    }
}
