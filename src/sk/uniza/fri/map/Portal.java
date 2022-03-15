package sk.uniza.fri.map;

import sk.uniza.fri.entities.Player;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.ESoundList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;
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
        ESoundList.playSound(ESoundList.PORTAL);
        Portal portal = this.portalGroup.getTheOtherPortal(this);
        mapHandler.changeMap(portal.getMap());
        player.getPosition().setPosition(portal.getPosition());
    }

    public Map getMap() {
        return this.map;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.getImage(), this.getPosition().getCoordX() - 29 / 2, this.getPosition().getCoordY() - 126 / 2, 29, 126, null);
    }
}
