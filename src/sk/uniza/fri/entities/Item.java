package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

public abstract class Item extends Entity {

    public static void spawnItem(Map map, ItemStack item, Position position) {
        switch (item.getItem()) {
            case COINS -> map.addItem(new ItemCoins((int)position.getX(), (int)position.getY(), item.getAmount()));
            case HEALTH_POTION -> map.addItem(new ItemHealthPotion((int)position.getX(), (int)position.getY()));
        }
    }

    public Item(EImageList[] images) {
        super(images, ERenderLayer.ITEMS);
    }

    public abstract ItemStack pickup();
}
