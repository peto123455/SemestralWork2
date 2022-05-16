package sk.uniza.fri.essentials;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.uniza.fri.entities.Chest;
import sk.uniza.fri.entities.Enemy;
import sk.uniza.fri.entities.EnemyKnight;
import sk.uniza.fri.entities.EnemyMageBoss;
import sk.uniza.fri.entities.Item;
import sk.uniza.fri.entities.Npc;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EPortalStatus;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.map.Portal;
import sk.uniza.fri.map.PortalGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class EntityLoader {

    public static final int PORTALS_PER_GROUP = 2;

    /**
     * Stará sa o načítanie všetkých entít na mapu
     * @param mapHandler Map Handler
     * @param player Hráč
     */
    public static void loadEntities(MapHandler mapHandler, Player player) {
        //Načítanie z JSONu
        JSONObject json = null;

        try {
            InputStream is = Enemy.class.getResourceAsStream("/maps/entities.json");
            JSONParser jsonParser = new JSONParser();
            json = (JSONObject)jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        EntityLoader.loadItems(json, mapHandler);
        EntityLoader.loadEnemies(json, mapHandler, player);
        EntityLoader.loadPortals(json, mapHandler);
        EntityLoader.loadChests(json, mapHandler);
        EntityLoader.loadNpcs(json, mapHandler);

        //Ručné vytvorenie bossa, zbytočné ho pridávať do JSONu keď je len jeden
        Enemy enemy = new EnemyMageBoss(new Position(1000, 550), mapHandler.getMap(3));
        enemy.follow(player);
        mapHandler.getMap(3).addEnemy(enemy);
    }

    //Private

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
            Map map = mapHandler.getMap(((Long)enemy.get("map")).intValue());

            Enemy enemyI = new EnemyKnight(position, map);
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
            map.addEnemy(enemyI);
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
                Map map = mapHandler.getMap(((Long)portal.get("map")).intValue());

                portals[i] = new Portal(new Position(x, y), map);

                if (portal.get("open") != null) {
                    portals[i].setStatus(EPortalStatus.valueOf((String)portal.get("open")));
                }

                map.addPortal(portals[i]);
            }

            new PortalGroup(portals[0], portals[1]);
        }
    }

    private static void loadChests(JSONObject json, MapHandler mapHandler) {
        for (Object chestObject : (JSONArray)json.get("chests")) {
            JSONObject chest = (JSONObject)chestObject;
            Position position = new Position(((Long)chest.get("x")).intValue(), ((Long)chest.get("y")).intValue());
            Map map = mapHandler.getMap(((Long)chest.get("map")).intValue());

            Chest chestInstance = new Chest(map, position);

            JSONArray items = (JSONArray)chest.get("items");

            for (Object itemObject : items) {
                JSONObject item = (JSONObject)itemObject;
                ItemStack itemStack = new ItemStack(EItemList.valueOf((String)item.get("item")), ((Long)item.get("amount")).intValue());
                chestInstance.addItem(itemStack);
            }

            map.addChest(chestInstance);
        }
    }

    private static void loadNpcs(JSONObject json, MapHandler mapHandler) {
        for (Object npcObject : (JSONArray)json.get("npcs")) {
            JSONObject npc = (JSONObject)npcObject;
            Position position = new Position(((Long)npc.get("x")).intValue(), ((Long)npc.get("y")).intValue());
            Map map = mapHandler.getMap(((Long)npc.get("map")).intValue());
            EImageList image = EImageList.valueOf((String)npc.get("image"));

            Npc npcI = new Npc(position, image, map);

            try {
                npcI.setQuest(Class.forName((String)npc.get("quest")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            map.addNpc(npcI);
        }
    }
}
