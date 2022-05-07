package sk.uniza.fri.map;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.ETileList;
import sk.uniza.fri.essentials.EntityLoader;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapHandler {
    private static final int MAPS_COUNT = 4;

    private final Map[] maps;
    private Map currentMap;

    public MapHandler(Game game) {
        this.maps = new Map[MapHandler.MAPS_COUNT];

        this.loadMaps(game);
        EntityLoader.loadEntities(this, game.getPlayer());
    }

    private void loadMaps(Game game) {
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

    public Map getMap() {
        return this.currentMap;
    }

    public Map getMap(int map) {
        return this.maps[map];
    }

    public void drawTiles(Graphics2D g2d) {
        for (int i = 0; i < this.getSizeY() ; ++i) {
            for (int j = 0; j < this.getSizeX(); ++j) {
                if (this.getTile(j, i) == null) {
                    continue;
                }

                this.getTile(j, i).draw(g2d, i, j);

            }
        }
    }

    public void drawEntities(Graphics2D g2d) {
        for (Entity entity : this.getMap().getEntityList()) {
            entity.draw(g2d);
        }
    }

    public boolean action(Player player) {
        return this.currentMap.action(player, this);
    }

    public void checkForItems(Player player) {
        this.currentMap.checkForItems(player);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.currentMap.getEnemies();
    }

}
