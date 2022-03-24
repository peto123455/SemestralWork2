package sk.uniza.fri.essentials;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.map.Portal;
import sk.uniza.fri.map.PortalGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class EntityLoader {

    public static final int PORTALS_PER_GROUP = 2;

    public static void loadEntities(MapHandler mapHandler, Player player) {
        JSONObject json = null;

        try {
            InputStream is = Enemy.class.getResourceAsStream("/maps/entities.json");
            JSONParser jsonParser = new JSONParser();
            json = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EntityLoader.loadItems(json, mapHandler);
        EntityLoader.loadEnemies(json, mapHandler, player);
        EntityLoader.loadPortals(json, mapHandler);
    }


    private static void loadItems(JSONObject json, MapHandler mapHandler) {
        for (Object itemObject : (JSONArray)json.get("items")) {
            JSONObject item = (JSONObject)itemObject;
            Position position = new Position(((Long)item.get("x")).intValue(), ((Long)item.get("y")).intValue());
            Map map = mapHandler.getMap(((Long)item.get("map")).intValue());

            ItemStack itemStack = new ItemStack(EItemList.valueOf((String)item.get("item")), ((Long)item.get("amount")).intValue());

            Item.spawnItem(map, itemStack, position);
        }
    }

    private static void loadEnemies(JSONObject json, MapHandler mapHandler, Player player) {
        for (Object enemyObject : (JSONArray)json.get("enemies")) {
            JSONObject enemy = (JSONObject)enemyObject;
            Position position = new Position(((Long)enemy.get("x")).intValue(), ((Long)enemy.get("y")).intValue());

            Enemy enemyI = new Enemy(position, mapHandler.getMap(((Long)enemy.get("map")).intValue()));
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
            mapHandler.getMap(((Long)enemy.get("map")).intValue()).getEnemies().add(enemyI);
        }
    }

    private static void loadPortals(JSONObject json, MapHandler mapHandler) {
        for (Object groupObject : (JSONArray)json.get("portal-groups")) {
            JSONArray group = (JSONArray)groupObject;

            Portal[] portals = new Portal[EntityLoader.PORTALS_PER_GROUP];

            for (int i = 0; i < portals.length; ++i) {
                JSONObject portal = (JSONObject)group.get(i);
                int x = ((Long)portal.get("x")).intValue();
                int y = ((Long)portal.get("y")).intValue();
                int map = ((Long)portal.get("map")).intValue();

                portals[i] = new Portal(new Position(x, y), mapHandler.getMap(map));
                mapHandler.getMap(map).getPortals().add(portals[i]);
            }

            new PortalGroup(portals[0], portals[1]);
        }
    }
}
