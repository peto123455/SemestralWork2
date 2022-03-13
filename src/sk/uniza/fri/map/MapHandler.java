package sk.uniza.fri.map;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.essentials.ETileList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapHandler {
    private static final int MAPS_COUNT = 2;

    private Map[] maps;
    private Map currentMap;

    public MapHandler() {
        //this.currentMap = 0;
        this.maps = new Map[MapHandler.MAPS_COUNT];

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
        this.currentMap = this.maps[0];
    }

    public void setTile(int x, int y, ETileList tile) {
        this.currentMap.setTile(x, y, tile);
    }

    public GameTile getTile(int x, int y) {
        return this.currentMap.getTile(x, y);
    }

    public int getSizeX() {
        return this.currentMap.getSizeX();
    }

    public int getSizeY() {
        return this.currentMap.getSizeY();
    }

    public void changeMap(Map map) {
        this.currentMap = map;
    }

    public ArrayList<Item> getItems() {
        return this.currentMap.getItems();
    }

    public ArrayList<Enemy> getEnemies() {
        return this.currentMap.getEnemies();
    }

    public Map getMap() {
        return this.currentMap;
    }

    public Map getMap(int map) {
        return this.maps[map];
    }

    public ArrayList<Portal> getPortals(Map map) {
        return this.currentMap.getPortals();
    }

    public ArrayList<Portal> getPortals() {
        return this.getPortals(currentMap);
    }
}
