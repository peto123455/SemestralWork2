package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;


public class EnemyKnight extends Enemy {
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

    @Override
    protected void onDeath() {
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
        super.onDeath();
    }

    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, 50)) {
            this.hit(player);
        }
    }

    public void hit(Player player) {
        if (super.canHit()) {
            ESoundList.playSound(ESoundList.SOWRD_STAB);
            new ParticleSlash(super.getPosition(), super.getDirection());
            player.takeHeart();
        }
    }
}
