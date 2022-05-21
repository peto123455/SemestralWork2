package entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.quests.QuestHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends EntityAlive {
    private long lastHit;
    private int cooldown;
    private EDirection eDirection;
    private Entity follow;
    private final ArrayList<ItemStack> drops;
    private final Map map;
    private int followDistance;

    public Enemy(Position position, Map map, EImageList[] images, int maxHearts) {
        super(images, maxHearts);

        super.getPosition().setPosition(position);

        this.drops = new ArrayList<>();
        this.map = map;

        this.lastHit = System.currentTimeMillis();
        this.cooldown = 1000;
        this.followDistance = 40;

        this.eDirection = EDirection.LEFT;
    }

    /**
     * Nastaví cooldown útoku
     * @param cooldown Dĺžka cooldownu
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public boolean update(Game game) {
        if (super.isDead()) {
            return false;
        }

        super.update(game);
        return true;
    }

    /**
     Zistí, či už nepriateľ môže zaútočiť na hráča (Cooldown)
     * @return Vráti, či nepriateľ môže zaútočiť
     */
    public boolean canHit() {
        long temp = System.currentTimeMillis();
        if (temp < this.lastHit + this.cooldown) {
            return false;
        }
        this.lastHit = temp + this.cooldown;
        return true;
    }

    /**
     * Vráti smer nepriateľa
     * @return Vráti smer nepriateľa
     */
    public EDirection getDirection() {
        return this.eDirection;
    }

    /**
     * Nepriateľ bude nasledovať entitu
     * @param entity Entita na sledovanie
     */
    public void follow(Entity entity) {
        this.follow = entity;
    }

    @Override
    public BufferedImage getImage() {
        if (this.eDirection == EDirection.LEFT) {
            return ImageTools.flip(super.getImage());
        }
        return super.getImage();
    }

    /**
     * Pridá item, ktorý vypadne po smrti
     * @param item Item na pridanie
     */
    public void addDropItem(ItemStack item) {
        this.drops.add(item);
    }

    /**
     * Nastaví vzdialenosť, na ktorú bude následovať entitu
     * @param followDistance Vzdialenosť
     */
    public void setFollowDistance(int followDistance) {
        this.followDistance = followDistance;
    }

    /**
     * Vráti splnenú úlohu do questu (Pri smrti)
     * @param questHandler Quest handler
     */
    public abstract void getQuestEvent(QuestHandler questHandler);

    /**
     * Zistí, či nepraiteľ môže videiť hráča
     * @param player Hráč
     * @param mapHandler Map Handler
     * @return Vráti, či nepriateľ vidí hráča
     */
    protected boolean canSeePlayer(Player player, MapHandler mapHandler) {
        Vector vector = new Vector((int)player.getPosition().getX() - (int)super.getPosition().getX(), (int)player.getPosition().getY() - (int)super.getPosition().getY());
        for (int i = 1; i < (int)vector.length() / 20; ++i) {
            Vector vectorPart = new Vector(vector.getX(), vector.getY());
            vectorPart.normalize();
            Position gridPos = Position.getPositionRelativeToGrid(new Position((int)this.getPosition().getX() + (int)(vectorPart.getX() * i * 20), (int)this.getPosition().getY() + (int)(vectorPart.getY() * i * 20)));

            GameTile tile = mapHandler.getTile((int)gridPos.getX(), (int)gridPos.getY());
            if (tile != null && tile.hasCollision()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDeath() {
        super.setRenderLayer(ERenderLayer.CORPSES);

        ESoundList.playSound(ESoundList.DEATH);
        this.dropItems();
        this.map.onEnemyDeath();
    }

    @Override
    protected EDirection updatePos(Game game) {
        Position position;

        if (this.follow != null && this.canSeePlayer(game.getPlayer(), game.getMapHandler())) {
            if (this.isNearEntity(this.follow, this.followDistance)) {
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

    //Private

    /**
     * Vyhodí veci na zem (Pri smrti)
     */
    private void dropItems() {
        for (ItemStack item : this.drops) {
            Random rand = new Random();
            Item.spawnItem(this.map, item, new Position((int)this.getPosition().getX() + rand.nextInt(50) - 25, (int)this.getPosition().getY() + rand.nextInt(50) - 25));
        }
    }
}
