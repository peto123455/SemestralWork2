package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;

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

    @Override
    public boolean update(Game game) {
        if (super.wallHit(game)) {
            new ParticleExplosion(this.getPosition());
            super.removeProjectile(this);
        } else if (super.isNearEntity(game.getPlayer(), 30)) {
            new ParticleExplosion(this.getPosition());
            super.removeProjectile(this);
            game.getPlayer().takeHeart();
        }
        return super.update(game);
    }
}
