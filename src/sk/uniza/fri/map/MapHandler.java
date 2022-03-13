package sk.uniza.fri.map;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.essentials.ETileList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapHandler {
    private static final int MAPS_COUNT = 1;

    private Map[] maps;
    private int currentMap;

    public MapHandler() {
        this.currentMap = 0;
        this.maps = new Map[1];

        this.loadMaps();
    }

    private void loadMaps() {
        for (int map = 0; map < MapHandler.MAPS_COUNT; ++map) {
            InputStream mapFile = this.getClass().getResourceAsStream(String.format("/maps/map%02d.txt", map + 1));
            Scanner scanner = new Scanner(mapFile);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            this.maps[map] = new Map(x, y);
            for (int i = 0; i < y; ++i) {
                for (int j = 0; j < x; ++j) {
                    this.maps[map].setTile(j, i, ETileList.getByID(scanner.nextInt()));
                }
            }
        }
    }

    public void setTile(int x, int y, ETileList tile) {
        this.maps[currentMap].setTile(x, y, tile);
    }

    public GameTile getTile(int x, int y) {
        return maps[currentMap].getTile(x, y);
    }

    public int getSizeX() {
        return this.maps[currentMap].getSizeX();
    }

    public int getSizeY() {
        return this.maps[currentMap].getSizeY();
    }

    public void changeMap(int map) {
        if (currentMap < 0 || currentMap >= this.maps.length) {
            return;
        }
        this.currentMap = map;
    }

    public ArrayList<Item> getItems() {
        return this.maps[currentMap].getItems();
    }

    public ArrayList<Enemy> getEnemies() {
        return this.maps[currentMap].getEnemies();
    }

    public Map getMan() {
        return this.maps[currentMap];
    }
}
