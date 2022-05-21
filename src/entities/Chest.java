package entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Chest extends Entity {
    private ArrayList<ItemStack> items;
    private boolean isOpen;
    private Map map;

    /**
     * Vytvorí chestku s predmetmi
     * @param map Mapa
     * @param position Pozícia
     * @param items Zoznam predmetov
     */
    public Chest(Map map, Position position, ItemStack[] items) {
        super(new EImageList[] { EImageList.CHEST, EImageList.CHEST_OPEN }, ERenderLayer.CHESTS);

        this.items = new ArrayList<>();
        this.isOpen = false;
        super.getPosition().setPosition(position);
        this.map = map;

        if (items != null) {
            this.addItems(items);
        }

        this.map.addChest(this);
    }

    /**
     * Vytvorí prázdnu chestku
     * @param map Mapa
     * @param position Pozícia
     */
    public Chest(Map map, Position position) {
        this(map, position, null);
    }

    /**
     * Otvorí bedňu
     * @return Ak bola otvorená
     */
    public boolean openChest() {
        if (this.isOpen) {
            return false;
        }

        Random rand = new Random();

        for (ItemStack item : this.items) {
            Item.spawnItem(this.map, item, new Position((int)super.getPosition().getX() + rand.nextInt(100) - 50, (int)super.getPosition().getY() + rand.nextInt(100) - 50));
        }

        ESoundList.playSound(ESoundList.CHEST);

        this.isOpen = true;
        return true;
    }

    /**
     * Pridá predmet do bedňe
     * @param item Predmet
     */
    public void addItem(ItemStack item) {
        this.items.add(item);
    }

    @Override
    public BufferedImage getImage () {
        if (this.isOpen) {
            return super.getImage(1);
        }
        return super.getImage(0);
    }

    //Private

    /**
     * Pridá predmety do bedňe
     * @param items Predmety
     */
    private void addItems(ItemStack[] items) {
        this.items.addAll(Arrays.asList(items));
    }
}
