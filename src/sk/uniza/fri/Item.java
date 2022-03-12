package sk.uniza.fri;

import java.util.ArrayList;

public abstract class Item extends Entity {
    private static ArrayList<Item> spawnedItems = new ArrayList<>();

    public Item(EImageList[] images) {
        super(images);
        Item.spawnedItems.add(this);
    }

    public abstract void pickup();

    public static ArrayList<Item> getSpawnedItems() {
        return spawnedItems;
    }
}
