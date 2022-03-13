package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.ItemStack;

public abstract class Item extends Entity {

    public Item(EImageList[] images) {
        super(images);
    }

    public abstract ItemStack pickup();
}
