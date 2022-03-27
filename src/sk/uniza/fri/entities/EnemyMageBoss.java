package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;

import java.awt.Graphics2D;


public class EnemyMageBoss extends Enemy {
    public EnemyMageBoss(Position position, Map map) {
        super(position, map, new EImageList[] {EImageList.MAGE_BOSS}, 2);
        super.setSpeed(2);

        super.setCooldown(400);
        super.setFollowDistance(350);
    }

    @Override
    public boolean update(Game game) {
        if (!super.update(game)) {
            return false;
        }

        if (super.canSeePlayer(game.getPlayer(), game.getMapHandler()) && super.canHit()) {
            new ProjectileMage(super.getPosition(), game.getPlayer().getPosition());
        }

        return true;
    }

    @Override
    protected void onDeath() {
        super.changeImages(new EImageList[] {EImageList.MAGE_BOSS_DEAD});
        super.onDeath();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(super.getImage(), this.getPosition().getIntCoordX() - super.getImage().getWidth(), this.getPosition().getIntCoordY() - super.getImage().getHeight(), super.getImage().getWidth() * 2, super.getImage().getHeight() * 2, null);
    }
}
