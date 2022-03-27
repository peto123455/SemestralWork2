package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

public abstract class Item extends Entity {

    public static void spawnItem(Map map, ItemStack item, Position position) {
        switch (item.getItem()) {
            case COINS -> map.addItem(new ItemCoins(position.getIntCoordX(), position.getIntCoordY(), item.getAmount()));
            case HEALTH_POTION -> map.addItem(new ItemHealthPotion(position.getIntCoordX(), position.getIntCoordY()));
        }
    }

    public Item(EImageList[] images) {
        super(images);
    }

    public abstract ItemStack pickup();
}
