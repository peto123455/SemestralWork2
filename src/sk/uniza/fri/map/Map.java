package sk.uniza.fri.map;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.ItemCoins;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.essentials.ETileList;

import java.util.ArrayList;

public class Map {
    private GameTile[][] mapLayout;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;

    public Map(int sizeX, int sizeY) {
        this.mapLayout = new GameTile[sizeX][sizeY];
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();

        //this.enemies.get(0).goToPos(new Position(150, 80));
    }

    public void setTile(int x, int y, ETileList tile) {
        if (tile == null) {
            this.mapLayout[x][y] = null;
            return;
        }

        this.mapLayout[x][y] = new GameTile(tile);
    }

    public GameTile getTile(int x, int y) {
        return mapLayout[x][y];
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
}
