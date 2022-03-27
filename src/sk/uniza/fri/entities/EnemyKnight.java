package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;


public class EnemyKnight extends Enemy {
    public EnemyKnight(Position position, Map map) {
        super(position, map, new EImageList[] {EImageList.KNIGHT}, 2);

        super.setCooldown(1000);
    }

    @Override
    protected void onDeath() {
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
        super.onDeath();
    }
}
