package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

public abstract class Item extends Entity {

    /**
     * Vytvorí item v hre (Ako entitu)
     * @param map
     * @param item
     * @param position
     */
    public static void spawnItem(Map map, ItemStack item, Position position) {
        item.getItemType().getItem().spawnItem(map, item, position);
    }

    /**
     * Item v hre (Ako entita)
     * @param images Obrázky
     */
    public Item(EImageList[] images) {
        super(images, ERenderLayer.ITEMS);
    }

    /**
     * Volá sa pri zobratí
     * @return Vracia zdvihnutý item
     */
    public abstract ItemStack pickup();
}
