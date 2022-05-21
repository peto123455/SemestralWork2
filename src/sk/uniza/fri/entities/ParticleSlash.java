package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;

public class ParticleSlash extends Particle {
    private EDirection direction;

    /**
     * Vytvorí efekt seknutia
     * @param position Pozícia seknutia
     * @param direction Smer seknutia
     */
    public ParticleSlash(Position position, EDirection direction) {
        super(position, new EImageList[]{EImageList.SLASH1, EImageList.SLASH2, EImageList.SLASH3}, 3);
        super.getPosition().addPosition(direction.getPos(27));
        this.direction = direction;
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == EDirection.LEFT) {
            return ImageTools.flip(super.getImage());
        }

        return super.getImage();
    }
}
