package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends EntityAlive {
    private long lastHit;
    private int cooldown;
    private EDirection eDirection;
    private Entity follow;
    private final ArrayList<ItemStack> drops;
    private final Map map;

    public Enemy(Map map) {
        this(new Position(0, 0), map);
    }

    public static void drawEnemies(Graphics2D g2d, MapHandler mapHandler) {
        ArrayList<Enemy> enemies = mapHandler.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }
    }

    public Enemy(Position position, Map map) {
        super(new EImageList[] {EImageList.KNIGHT}, 2);

        super.getPosition().setPosition(position);

        this.drops = new ArrayList<>();
        this.map = map;

        this.lastHit = System.currentTimeMillis();
        this.cooldown = 1000;

        this.eDirection = EDirection.LEFT;
    }

    @Override
    public void update(Game game) {
        if (super.isDead()) {
            return;
        }

        super.update(game);
        this.checkForPlayer(game.getPlayer());
    }

    private boolean canSeePlayer(Player player, MapHandler mapHandler) {
        Vector vector = new Vector(player.getPosition().getCoordX() - super.getPosition().getCoordX(), player.getPosition().getCoordY() - super.getPosition().getCoordY());
        for (int i = 1; i < (int)vector.length() / 20; ++i) {
            Vector vectorPart = new Vector(vector.getX(), vector.getY());
            vectorPart.normalize();
            Position girdPos = Position.getPositionRelativeToGrid(new Position(this.getPosition().getCoordX() + (int)(vectorPart.getX() * i * 20), this.getPosition().getCoordY() + (int)(vectorPart.getY() * i * 20)));

            GameTile tile = mapHandler.getTile(girdPos.getCoordX(), girdPos.getCoordY());
            if (tile != null && tile.hasCollision()) {
                return false;
            }
        }
        return true;
    }

    public void hit(EntityAlive entity) {
        long temp = System.currentTimeMillis();
        if (temp < this.lastHit + this.cooldown) {
            return;
        }
        ESoundList.playSound(ESoundList.SOWRD_STAB);
        new ParticleSlash(super.getPosition(), this.eDirection);
        entity.takeHeart();
        this.lastHit = temp + this.cooldown;
    }

    @Override
    protected void onDeath() {
        ESoundList.playSound(ESoundList.DEATH);
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
        this.dropItems();
        this.map.onEnemyDeath();
    }

    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, 50)) {
            this.hit(player);
        }
    }

    public void follow(Entity entity) {
        this.follow = entity;
    }

    @Override
    protected EDirection updatePos(Game game) {
        Position position;

        if (this.follow != null && this.canSeePlayer(game.getPlayer(), game.getMapHandler())) {
            if (this.isNearEntity(this.follow, 40)) {
                return null;
            }
            position = this.follow.getPosition();
        } else if (super.getToPos() != null) {
            position = super.getToPos();
        } else {
            return null;
        }

        EDirection direction = super.updatePos(game, position);
        if (direction != null) {
            this.eDirection = direction;
        }

        return null;
    }

    @Override
    public BufferedImage getImage() {
        if (this.eDirection == EDirection.LEFT) {
            return ImageTools.flip(super.getImage());
        }
        return super.getImage();
    }

    public void addDropItem(ItemStack item) {
        this.drops.add(item);
    }

    private void dropItems() {
        for (ItemStack item : this.drops) {
            Random rand = new Random();
            Item.spawnItem(this.map, item, new Position(this.getPosition().getCoordX() + rand.nextInt(50) - 25, this.getPosition().getCoordY() + rand.nextInt(50) - 25));
        }
    }
}
