package sk.uniza.fri.map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.essentials.ETileList;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapHandler {
    private static final int MAPS_COUNT = 3;

    private Map[] maps;
    private Map currentMap;

    public MapHandler(Game game) {
        this.maps = new Map[MapHandler.MAPS_COUNT];

        this.loadMaps(game);
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

        this.spawnEntities(game);
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

    private void spawnEntities(Game game) {
        JSONObject json = null;

        try {
            InputStream is = Enemy.class.getResourceAsStream("/maps/entities.json");
            JSONParser jsonParser = new JSONParser();
            json = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        } catch (ParseException e) {

        }

        this.spawnItems(json);
        this.spawnEnemies(json, game.getPlayer());
    }

    private void spawnItems(JSONObject json) {
        for (Object itemObject : (JSONArray)json.get("items")) {
            JSONObject item = (JSONObject)itemObject;
            Position position = new Position(((Long)item.get("x")).intValue(), ((Long)item.get("y")).intValue());
            Map map = this.getMap(((Long)item.get("map")).intValue());

            ItemStack itemStack = new ItemStack(EItemList.valueOf((String)item.get("item")), ((Long)item.get("amount")).intValue());

            Item.spawnItem(map, itemStack, position);
        }
    }

    private void spawnEnemies(JSONObject json, Player player) {
        for (Object enemyObject : (JSONArray)json.get("enemies")) {
            JSONObject enemy = (JSONObject)enemyObject;
            Position position = new Position(((Long)enemy.get("x")).intValue(), ((Long)enemy.get("y")).intValue());

            Enemy enemyI = new Enemy(position, this.getMap(((Long)enemy.get("map")).intValue()));
            if (enemy.get("followPlayer") != null && (Boolean)enemy.get("followPlayer")) {
                enemyI.follow(player);
            }

            if (enemy.get("hearts") != null) {
                enemyI.setHearts(((Long)enemy.get("hearts")).intValue());
            }

            if (enemy.get("goToPos") != null) {
                JSONObject goToPos = (JSONObject)enemy.get("goToPos");
                enemyI.goToPos(new Position(((Long)goToPos.get("x")).intValue(), ((Long)goToPos.get("y")).intValue()));
            }

            for (Object itemObject : (JSONArray)enemy.get("items")) {
                JSONObject item = (JSONObject)itemObject;
                enemyI.addDropItem(new ItemStack(EItemList.valueOf((String)item.get("name")), ((Long)item.get("amount")).intValue()));
            }
            this.getMap(((Long)enemy.get("map")).intValue()).getEnemies().add(enemyI);
        }
    }
}
