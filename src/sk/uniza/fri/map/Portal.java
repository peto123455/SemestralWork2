package sk.uniza.fri.map;

import sk.uniza.fri.entities.Player;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Position;

import java.awt.image.BufferedImage;

public class Portal {
    private BufferedImage image;
    private Position position;
    private PortalGroup portalGroup;
    private Map map;

    public Portal(Position position, Map map) {
        this.image = EImageList.PORTAL.getImage();
        this.position = position;
        this.map = map;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Position getPosition() {
        return this.position;
    }

    public PortalGroup getPortalGroup() {
        return this.portalGroup;
    }

    public void setPortalGroup(PortalGroup portalGroup) {
        this.portalGroup = portalGroup;
    }

    public void teleport(Player player, MapHandler mapHandler) {
        Portal portal = this.portalGroup.getTheOtherPortal(this);
        mapHandler.changeMap(portal.getMap());
        player.getPosition().setPosition(portal.getPosition());
    }

    public Map getMap() {
        return this.map;
    }
}
