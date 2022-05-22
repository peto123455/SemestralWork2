package sk.uniza.fri.map;

import sk.uniza.fri.entities.Chest;
import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Npc;
import sk.uniza.fri.entities.Player;
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

    /**
     * Mapa
     * @param sizeX Počet blokov - Riadok
     * @param sizeY Počet blokov - Stĺpec
     */
    public Map(int sizeX, int sizeY) {
        this.mapLayout = new GameTile[sizeX][sizeY];
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.portals = new ArrayList<>();
        this.chests = new ArrayList<>();
    }

    /**
     * Nastaví blok na súradniciach
     * @param x Súradnica X
     * @param y Súradnica Y
     * @param tile Blok
     */
    public void setTile(int x, int y, ETileList tile) {
        if (tile == null) {
            this.mapLayout[x][y] = null;
            return;
        }

        this.mapLayout[x][y] = new GameTile(tile);
    }

    /**
     * @param x Súradnica X
     * @param y Súradnica Y
     * @return Blok na daných súradniciach
     */
    public GameTile getTile(int x, int y) {
        return this.mapLayout[x][y];
    }

    /**
     * @return Počet blokov na riadok
     */
    public int getSizeX() {
        return this.mapLayout.length;
    }

    /**
     * @return Počet blokov na stĺpec
     */
    public int getSizeY() {
        return this.mapLayout[0].length;
    }

    /**
     * @return Zoznam nepriateĺov
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Pridá item na mapu
     * @param item Item na pridanie
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Kontroluje, či sa hráč nachádza pri iteme
     * @param player Hráč
     */
    public void checkForItems(Player player) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (player.isNearEntity(this.items.get(i), 30)) {
                player.getInventory().addItemStack(this.items.remove(i).pickup());
            }
        }
    }

    /**
     * Volá sa pri úmrti nepriateľa, kontroluje, či sú všetci mŕtvy.
     */
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

    /**
     * Otvorí všetky portály
     */
    public void openPortals() {
        for (Portal portal : this.portals) {
            portal.setStatus(null);
        }
    }

    /**
     * Pridá bedňu na mapu
     * @param chest Bedňa
     */
    public void addChest(Chest chest) {
        if (this.chests.contains(chest)) {
            return;
        }

        this.chests.add(chest);
    }

    /**
     * Pridá Portál na mapu
     * @param portal Portál
     */
    public void addPortal(Portal portal) {
        if (this.portals.contains(portal)) {
            return;
        }

        this.portals.add(portal);
    }

    /**
     * Pridá Nepriatela na mapu
     * @param enemy Nepriatel
     */
    public void addEnemy(Enemy enemy) {
        if (this.enemies.contains(enemy)) {
            return;
        }

        this.enemies.add(enemy);
    }

    /**
     * Pridá NPC na mapu
     * @param npc NPC
     */
    public void addNpc(Npc npc) {
        if (this.npcs.contains(npc)) {
            return;
        }

        this.npcs.add(npc);
    }

    /**
     * @return Zoznam entít na mape
     */
    public ArrayList<Entity> getEntityList() {
        ArrayList<Entity> entities = new ArrayList<>();

        entities.addAll(this.items);
        entities.addAll(this.chests);
        entities.addAll(this.portals);
        entities.addAll(this.npcs);
        entities.addAll(this.enemies);

        return entities;
    }

    /**
     * Volá sa pri vykonaní akcie hráča (Stlačenie E)
     * @param player Referencia na Hráča
     * @param mapHandler Referencia na Map Handler
     * @return Či sa vykonala nejaká akcia
     */
    public boolean action(Player player, MapHandler mapHandler) {
        for (Chest chest : this.chests) {
            if (player.isNearEntity(chest, 30) && chest.openChest()) {
                return true;
            }
        }

        for (Npc npc : this.npcs) {
            if (player.isNearEntity(npc, 30) && npc.checkQuest(player)) {
                return true;
            }
        }

        for (Portal portal : this.portals) {
            if (player.isNearEntity(portal, 80)) {
                portal.teleport(player, mapHandler);
                return true;
            }
        }

        return false;
    }
}
