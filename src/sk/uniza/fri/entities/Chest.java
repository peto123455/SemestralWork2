package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Chest extends Entity {
    private ArrayList<ItemStack> items;
    private boolean isOpen;
    private Map map;

    public Chest(Map map, Position position, ItemStack[] items) {
        super(new EImageList[] { EImageList.CHEST, EImageList.CHEST_OPEN });

        this.items = new ArrayList<>();
        this.addItems(items);
        this.isOpen = false;
        super.getPosition().setPosition(position);
        this.map = map;
        this.map.addChest(this);
    }

    public void openChest() {
        if (this.isOpen) {
            return;
        }

        Random rand = new Random();

        for (ItemStack item : this.items) {
            Item.spawnItem(this.map, item, new Position(super.getPosition().getCoordX() + rand.nextInt(100) - 50, super.getPosition().getCoordY() + rand.nextInt(100) - 50));
        }

        this.isOpen = true;
    }

    private void addItems(ItemStack[] items) {
        for (ItemStack item : items) {
            this.items.add(item);
        }
    }

    @Override
    public BufferedImage getImage () {
        if (this.isOpen) {
            return super.getImage(1);
        }
        return super.getImage(0);
    }
}
