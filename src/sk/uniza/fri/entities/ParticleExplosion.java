package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;

public class ParticleExplosion extends Particle {

    /**
     * Vytvorí efekt výbuchu
     * @param position Pozícia výbuchu
     */
    public ParticleExplosion(Position position) {
        super(position, new EImageList[]{EImageList.EXPLOSION1, EImageList.EXPLOSION2, EImageList.EXPLOSION3, EImageList.EXPLOSION4, EImageList.EXPLOSION5, EImageList.EXPLOSION6, EImageList.EXPLOSION7}, 3);
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }
}
