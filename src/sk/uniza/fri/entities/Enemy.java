package sk.uniza.fri.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ESoundList;
import sk.uniza.fri.essentials.HealthSystem;
import sk.uniza.fri.essentials.IEntityAlive;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity implements IEntityAlive {
    private HealthSystem healthSystem;
    private long lastHit;
    private int cooldown;
    private Direction direction;
    private Position toPos;
    private Entity follow;
    private boolean dead;
    private ArrayList<ItemStack> drops;
    private Map map;

    public Enemy(Map map) {
        this(new Position(0, 0), map);
    }

    public Enemy(Position position, Map map) {
        super(new EImageList[] {EImageList.KNIGHT});

        super.getPosition().setPosition(position);

        this.healthSystem = new HealthSystem(2);
        this.drops = new ArrayList<>();
        this.map = map;

        this.lastHit = System.currentTimeMillis();
        this.cooldown = 1000;

        this.direction = Direction.LEFT;
        this.dead = false;
    }

    public void update(Game game) {
        if (dead) {
            return;
        }

        this.updatePos(game);
        this.checkForPlayer(game.getPlayer());
    }

    private boolean canSeePlayer(Player player, MapHandler mapHandler) {
        Vector vector = new Vector(player.getPosition().getCoordX() - super.getPosition().getCoordX(), player.getPosition().getCoordY() - super.getPosition().getCoordY());
        for (int i = 1; i < (int)vector.length() / 20; ++i) {
            Position position = new Position(super.getPosition().getCoordX(), super.getPosition().getCoordY());
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

    public void hit(IEntityAlive entity) {
        long temp = System.currentTimeMillis();
        if (temp < lastHit + cooldown) {
            return;
        }
        ESoundList.playSound(ESoundList.SOWRD_STAB);
        new ParticleSlash(super.getPosition(), this.direction);
        entity.takeHeart();
        this.lastHit = temp + cooldown;
    }

    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
        }
        return taken;
    }

    private void onDeath() {
        this.dead = true;
        ESoundList.playSound(ESoundList.DEATH);
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
        this.dropItems();
    }

    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, 50)) {
            this.hit(player);
        }
    }

    public void goToPos(Position toPos) {
        this.toPos = toPos;
    }

    public void follow(Entity entity) {
        this.follow = entity;
    }

    private void updatePos(Game game) {
        Position position;

        if (this.follow != null && this.canSeePlayer(game.getPlayer(), game.getMapHandler())) {
            if (this.isNearEntity(follow, 40)) {
                return;
            }
            position = this.follow.getPosition();
        } else if (this.toPos != null) {
            position = this.toPos;
        } else {
            return;
        }

        Vector vector = new Vector(position.getCoordX() - super.getPosition().getCoordX(), position.getCoordY() - super.getPosition().getCoordY());
        //Obmedzenie maximálnej prejdenej vzialenosti na 4px
        if (vector.length() > 4) {
            vector.normalize();
            vector.multiply(4);
        }

        Position pos = new Position((int)vector.getX(), (int)vector.getY());
        if (pos.getCoordX() != 0 || pos.getCoordY() != 0) {
            //Zmena smeru
            if (pos.getCoordX() < 0) {
                this.direction = Direction.LEFT;
            } else if (pos.getCoordX() > 0) {
                this.direction = Direction.RIGHT;
            }

            this.move(new Position(pos.getCoordX(), 0), game.getMapHandler());
            this.move(new Position(0, pos.getCoordY()), game.getMapHandler());
        } else {
            this.toPos = null;
        }
    }

    private void move(Position byPos, MapHandler mapHandler) {
        //Systém kolízií
        Position futurePosition = new Position().addPosition(this.getPosition()).addPosition(byPos);
        futurePosition = Position.getPositionRelativeToGrid(futurePosition);
        GameTile tile = mapHandler.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
        if (tile != null && tile.hasCollision()) {
            return;
        }

        //Pohyb
        this.getPosition().addPosition(byPos);
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.LEFT) {
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

    public void setHearts(int amount) {
        this.healthSystem.setHearts(amount);
    }
}
