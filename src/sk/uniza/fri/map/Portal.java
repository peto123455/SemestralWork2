package sk.uniza.fri.map;

import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EPortalStatus;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;

public class Portal extends Entity {
    private PortalGroup portalGroup;
    private final Map map;
    private EPortalStatus status;

    public Portal(Position position, Map map) {
        super(new EImageList[] { EImageList.PORTAL });
        super.getPosition().setPosition(position);
        this.map = map;
    }

    public PortalGroup getPortalGroup() {
        return this.portalGroup;
    }

    public void setPortalGroup(PortalGroup portalGroup) {
        this.portalGroup = portalGroup;
    }

    public void teleport(Player player, MapHandler mapHandler) {
        if (this.status != null) {
            return;
        }

        ESoundList.playSound(ESoundList.PORTAL);
        Portal portal = this.portalGroup.getTheOtherPortal(this);
        mapHandler.changeMap(portal.getMap());
        player.getPosition().setPosition(portal.getPosition());
    }

    public Map getMap() {
        return this.map;
    }

    public void setStatus(EPortalStatus status) {
        this.status = status;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (this.status != null) {
            return;
        }

        g2d.drawImage(this.getImage(), this.getPosition().getCoordX() - 29 / 2, this.getPosition().getCoordY() - 126 / 2, 29, 126, null);
    }

    public EPortalStatus getStatus() {
        return this.status;
    }
}
