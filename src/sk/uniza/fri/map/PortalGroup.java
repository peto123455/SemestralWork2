package sk.uniza.fri.map;

public class PortalGroup {
    private Portal[] group;

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
