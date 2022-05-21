package entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;

public class ProjectileMage extends Projectile {
    /**
     * Vytvorí projektil mága
     * @param position Pozícia
     * @param toPos Pozícia, na ktorú ide strela
     */
    public ProjectileMage(Position position, Position toPos) {
        super(new EImageList[] { EImageList.PROJECILE_MAGE }, position, toPos);

        ESoundList.playSound(ESoundList.PROJECTILE_MAGIC);
    }
}
