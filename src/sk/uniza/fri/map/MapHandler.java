package sk.uniza.fri.map;

import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.ETileList;
import sk.uniza.fri.essentials.EntityLoader;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MapHandler {
    private static final int MAPS_COUNT = 4;

    private final Map[] maps;
    private Map currentMap;

    /**
     * Stará sa o správu a prepínanie medzi mapami.
     * @param game Referencia na Hru
     */
    public MapHandler(Game game) {
        this.maps = new Map[MapHandler.MAPS_COUNT];

        this.loadMaps(game);
        EntityLoader.loadEntities(this, game.getPlayer());
    }

    /**
     * Nastaví blok na daných súradniciach
     * @param x Súradnica X
     * @param y Súradnica Y
     * @param tile Blok
     */
    public void setTile(int x, int y, ETileList tile) {
        this.currentMap.setTile(x, y, tile);
    }

    /**
     * @param x Súradnica X
     * @param y Súradnica Y
     * @return Blok na súradniciach
     */
    public GameTile getTile(int x, int y) {
        return this.currentMap.getTile(x, y);
    }

    /**
     * @return Počet blokov na riadok na danej mape
     */
    public int getSizeX() {
        return this.currentMap.getSizeX();
    }

    /**
     * @return Počet blokov na stĺpec na danej mape
     */
    public int getSizeY() {
        return this.currentMap.getSizeY();
    }

    /**
     * Zmení aktuálnu mapu
     * @param map Mapa, na ktorú sa zmení
     */
    public void changeMap(Map map) {
        this.currentMap = map;
    }

    /**
     * @return Aktuálna mapa
     */
    public Map getMap() {
        return this.currentMap;
    }

    /**
     * @param map Id mapy
     * @return Mapa so zadaným id
     */
    public Map getMap(int map) {
        return this.maps[map];
    }

    /**
     * Stará sa o vykreslovanie mapy
     * @param g2d Plátno
     */
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

    /**
     * Stará sa o vykreslovanie entít na aktuálnej mape
     * @param g2d Plátno
     */
    public void drawEntities(Graphics2D g2d) {
        ArrayList<Entity> orderedByLayers = this.getMap().getEntityList();

        Collections.sort(orderedByLayers, new Comparator<Entity>() {
            @Override
            public int compare(Entity entity1, Entity entity2) {
                return entity1.getRenderLayer().ordinal() - entity2.getRenderLayer().ordinal();
            }
        });

        for (Entity entity : orderedByLayers) {
            entity.draw(g2d);
        }
    }

    /**
     * Volá sa pri vykonaní akcie (Stlačenie E)
     * @param player Hráč
     * @return Či sa vykonala akcia
     */
    public boolean action(Player player) {
        return this.currentMap.action(player, this);
    }

    /**
     * Skontroluje, či sa hráč nachádza pri itemoch, ak áno zoberie ho.
     * @param player
     */
    public void checkForItems(Player player) {
        this.currentMap.checkForItems(player);
    }

    /**
     * @return Zoznam nepriateľov
     */
    public ArrayList<Enemy> getEnemies() {
        return this.currentMap.getEnemies();
    }

    /**
     * Stará sa o načítanie máp
     * @param game Referencia na Hru
     */
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

}
