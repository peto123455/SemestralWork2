package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.HealthSystem;
import sk.uniza.fri.essentials.IEntityAlive;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;

public class Enemy extends Entity implements IEntityAlive {
    private HealthSystem healthSystem;
    private long lastHit;
    private int cooldown;

    public Enemy() {
        this(new Position(0, 0));
    }

    public Enemy(Position position) {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.healthSystem = new HealthSystem(5);
        this.lastHit = System.nanoTime();
        this.cooldown = 300000000;
        super.getPosition().setPosition(position);
    }

    public void update(Game game) {
        this.checkForPlayer(game.getPlayer());
    }

    public void hit(IEntityAlive entity) {
        if (System.nanoTime() < lastHit + cooldown) {
            return;
        }
        entity.getHealthSystem().takeHeart();
        this.lastHit = System.nanoTime() + cooldown;
    }

    public HealthSystem getHealthSystem() {
        return this.healthSystem;
    }

    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, 40)) {
            this.hit(player);
        }
    }
}
