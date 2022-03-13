package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;

public abstract class Item extends Entity {

    public static void spawnItem(Map map, ItemStack item, int x, int y) {
        switch (item.getItem()) {
            case COINS -> map.addItem(new ItemCoins(x, y, item.getAmount()));
        }
    }

    public Item(EImageList[] images) {
        super(images);
    }

    public abstract ItemStack pickup();
}
