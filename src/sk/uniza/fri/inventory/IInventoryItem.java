package sk.uniza.fri.inventory;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;

public interface IInventoryItem {
    /**
     * @return Vráti typ itemu
     */
    EItemList getType();

    /**
     * Použije predmet
     * @param game Referencia na hru
     * @return Či ho použilo
     */
    boolean useItem(Game game);

    /**
     * Vytvorí predmet v hre
     * @param map Referencia na mapu
     * @param item Predmet
     * @param position Jeho pozícia
     */
    void spawnItem(Map map, ItemStack item, Position position);
}
