package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.QuestHandler;

import java.awt.Graphics2D;


public class EnemyMageBoss extends Enemy {
    /**
     * Vytvorí bossa mága
     * @param position Pozícia
     * @param map Mapa
     */
    public EnemyMageBoss(Position position, Map map) {
        super(position, map, new EImageList[] {EImageList.MAGE_BOSS}, 4);
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
    public void draw(Graphics2D g2d) {
        g2d.drawImage(super.getImage(), (int)this.getPosition().getX() - super.getImage().getWidth(), (int)this.getPosition().getY() - super.getImage().getHeight(), super.getImage().getWidth() * 2, super.getImage().getHeight() * 2, null);
    }

    public void getQuestEvent(QuestHandler questHandler) {
        questHandler.onActionPerformed(EQuestAction.BOSS_KILLED, 1);
    }

    @Override
    protected void onDeath() {
        super.changeImages(new EImageList[] {EImageList.MAGE_BOSS_DEAD});
        super.onDeath();
    }
}
