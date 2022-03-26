package sk.uniza.fri.map;

import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;

public class Portal extends Entity {
    private PortalGroup portalGroup;
    private final Map map;
    private boolean isEnabled;

    public Portal(Position position, Map map) {
        super(new EImageList[] { EImageList.PORTAL });
        super.getPosition().setPosition(position);
        this.map = map;
        this.isEnabled = true;
    }

    public PortalGroup getPortalGroup() {
        return this.portalGroup;
    }

    public void setPortalGroup(PortalGroup portalGroup) {
        this.portalGroup = portalGroup;
    }

    public void teleport(Player player, MapHandler mapHandler) {
        if (!isEnabled) {
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

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!isEnabled) {
            return;
        }

        g2d.drawImage(this.getImage(), this.getPosition().getCoordX() - 29 / 2, this.getPosition().getCoordY() - 126 / 2, 29, 126, null);
    }
}
