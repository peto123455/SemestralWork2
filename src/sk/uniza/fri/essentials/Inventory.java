package sk.uniza.fri.essentials;

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
}
