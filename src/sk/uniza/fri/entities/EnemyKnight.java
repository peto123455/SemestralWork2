package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.QuestHandler;


public class EnemyKnight extends Enemy {
    private static final int ATTACK_RANGE = 50;

    public EnemyKnight(Position position, Map map) {
        super(position, map, new EImageList[] {EImageList.KNIGHT}, 2);

        super.setCooldown(1000);
    }

    @Override
    public boolean update(Game game) {
        if (!super.update(game)) {
            return false;
        }

        this.checkForPlayer(game.getPlayer());
        return true;
    }

    /**
     * Útok nepriateľa
     * @param player Hráč
     */
    public void hit(Player player) {
        if (super.canHit()) {
            ESoundList.playSound(ESoundList.SWORD_STAB);
            new ParticleSlash(super.getPosition(), super.getDirection());
            player.takeHeart();
        }
    }

    public void getQuestEvent(QuestHandler questHandler) {
        questHandler.onActionPerformed(EQuestAction.ENEMY_KILLED, 1);
    }

    @Override
    protected void onDeath() {
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
        super.onDeath();
    }

    //Private

    /**
     * Skontroluje, či je hráč v blízkosti na útok
     * @param player
     */
    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, EnemyKnight.ATTACK_RANGE)) {
            this.hit(player);
        }
    }
}
