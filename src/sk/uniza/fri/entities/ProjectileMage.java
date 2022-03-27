package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;

public class ProjectileMage extends Projectile {
    public ProjectileMage(Position position, Position toPos) {
        super(new EImageList[] { EImageList.PROJECILE_MAGE }, position, toPos);
    }
}
