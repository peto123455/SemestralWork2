package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

import java.awt.Graphics2D;


public class EnemyMageBoss extends Enemy {
    public EnemyMageBoss(Position position, Map map) {
        super(position, map, new EImageList[] {EImageList.MAGE_BOSS}, 2);
        super.setSpeed(2);

        super.setCooldown(1000);
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
