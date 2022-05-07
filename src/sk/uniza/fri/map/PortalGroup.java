package sk.uniza.fri.map;

import sk.uniza.fri.essentials.EntityLoader;

public class PortalGroup {
    private Portal[] group;

    /**
     * Portálová skupina. Slúži na spájanie 2 portálov.
     * Cestovanie A - B
     * @param portal1 Portál A
     * @param portal2 Portál B
     */
    public PortalGroup(Portal portal1, Portal portal2) {
        this.group = new Portal[EntityLoader.PORTALS_PER_GROUP];
        this.group[0] = portal1;
        this.group[1] = portal2;

        for (int i = 0; i < this.group.length; ++i) {
            this.group[i].setPortalGroup(this);
        }
    }

    /**
     * @param portal Jeden z portálov
     * @return Opačný portál k zadanému portálu zo skupiny
     */
    public Portal getTheOtherPortal(Portal portal) {
        return (this.group[0] == portal) ? this.group[1] : this.group[0];
    }

}
