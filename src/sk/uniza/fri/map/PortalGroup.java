package sk.uniza.fri.map;

import sk.uniza.fri.essentials.Position;

public class PortalGroup {
    private Portal[] group;

    public static void createPortals(MapHandler mapHandler) {
        Portal portal1 = new Portal(new Position(1000, 600), mapHandler.getMap(0));
        Portal portal2 = new Portal(new Position(100, 600), mapHandler.getMap(1));
        mapHandler.getMap(0).getPortals().add(portal1);
        mapHandler.getMap(1).getPortals().add(portal2);

        Portal portal3 = new Portal(new Position(1000, 600), mapHandler.getMap(1));
        Portal portal4 = new Portal(new Position(100, 200), mapHandler.getMap(2));
        mapHandler.getMap(1).getPortals().add(portal3);
        mapHandler.getMap(2).getPortals().add(portal4);

        new PortalGroup(portal1, portal2);
        new PortalGroup(portal3, portal4);
    }

    public PortalGroup(Portal portal1, Portal portal2) {
        this.group = new Portal[2];
        this.group[0] = portal1;
        this.group[1] = portal2;

        for (int i = 0; i < this.group.length; ++i) {
            this.group[i].setPortalGroup(this);
        }
    }

    public Portal getTheOtherPortal(Portal portal) {
        return (this.group[0] == portal) ? this.group[1] : this.group[0];
    }

}
