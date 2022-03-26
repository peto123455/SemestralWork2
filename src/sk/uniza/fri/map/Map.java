package sk.uniza.fri.map;

import sk.uniza.fri.entities.Chest;
import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Npc;
import sk.uniza.fri.enums.EPortalStatus;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.enums.ETileList;

import java.util.ArrayList;

public class Map {
    private final GameTile[][] mapLayout;
    private final ArrayList<Item> items;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Npc> npcs;
    private final ArrayList<Portal> portals;
    private final ArrayList<Chest> chests;

    public Map(int sizeX, int sizeY) {
        this.mapLayout = new GameTile[sizeX][sizeY];
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.portals = new ArrayList<>();
        this.chests = new ArrayList<>();
    }

    public void setTile(int x, int y, ETileList tile) {
        if (tile == null) {
            this.mapLayout[x][y] = null;
            return;
        }

        this.mapLayout[x][y] = new GameTile(tile);
    }

    public GameTile getTile(int x, int y) {
        return this.mapLayout[x][y];
    }

    public int getSizeX() {
        return this.mapLayout.length;
    }

    public int getSizeY() {
        return this.mapLayout[0].length;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Portal> getPortals() {
        return this.portals;
    }

    public void onEnemyDeath() {
        for (Enemy enemy : this.enemies) {
            if (!enemy.isDead()) {
                return;
            }
        }
        for (Portal portal : this.portals) {
            if (portal.getStatus() == EPortalStatus.OPEN_ON_ALL_DEAD) {
                portal.setStatus(null);
            }
        }
    }

    public void openPortals() {
        for (Portal portal : this.portals) {
            portal.setStatus(null);
        }
    }

    public void addChest(Chest chest) {
        if (this.chests.contains(chest)) {
            return;
        }

        this.chests.add(chest);
    }

    public void addNpc(Npc npc) {
        if (this.npcs.contains(npc)) {
            return;
        }

        this.npcs.add(npc);
    }

    public ArrayList<Chest> getChests() {
        return this.chests;
    }

    public ArrayList<Npc> getNpcs() {
        return this.npcs;
    }

    public ArrayList<Entity> getEntityList() {
        ArrayList<Entity> entities = new ArrayList<>();

        entities.addAll(this.items);
        entities.addAll(this.chests);
        entities.addAll(this.portals);
        entities.addAll(this.npcs);
        entities.addAll(this.enemies);

        return entities;
    }
}
