package sk.uniza.fri.essentials;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.main.Game;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<ItemStack> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public void addItemStack(ItemStack item) {
        ItemStack itemStack = this.containsItem(item);
        if (itemStack != null) {
            itemStack.addAmount(item.getAmount());
            return;
        }
        this.inventory.add(item);
    }

    public boolean removeItemStack(ItemStack item) {
        ItemStack itemStack = this.containsItem(item);
        if (itemStack == null || item.getAmount() > itemStack.getAmount()) {
            return false;
        }

        itemStack.setAmount(itemStack.getAmount() - item.getAmount());

        if (itemStack.getAmount() <= 0) {
            this.inventory.remove(itemStack);
        }

        return true;
    }

    private ItemStack containsItem(ItemStack item) {
        for (ItemStack itemStack : this.inventory) {
            if (itemStack.getItem() == item.getItem()) {
                return itemStack;
            }
        }
        return null;
    }

    public ItemStack containsItem(EItemList item) {
        return this.containsItem(new ItemStack(item, 0));
    }

    public int getItemAmount(EItemList item) {
        ItemStack itemStack = this.containsItem(item);
        return (itemStack != null) ? itemStack.getAmount() : 0;
    }

    @Override
    public String toString() {
        if (this.inventory.isEmpty()) {
            return "Empty";
        }

        String string = new String();
        for (ItemStack item : this.inventory) {
            string += String.format("%s: %dx\n", item.getItem().getName(), item.getAmount());
        }
        return string;
    }

    public boolean useItem(ItemStack item, Game game) {
        switch (item.getItem()) {
            case HEALTH_POTION -> {
                if (!game.getPlayer().isMaxHearts() && this.removeItemStack(item)) {
                    ESoundList.playSound(ESoundList.POTION_DRINK);
                    game.getPlayer().addHeart();
                    return true;
                }
                return false;
            }
        }

        return false;
    }
}
