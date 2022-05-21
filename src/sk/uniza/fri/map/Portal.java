package sk.uniza.fri.map;

import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Player;
import sk.uniza.fri.entities.Projectile;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EPortalStatus;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.Position;

import java.awt.Graphics2D;

public class Portal extends Entity {
    private PortalGroup portalGroup;
    private final Map map;
    private EPortalStatus status;

    /**
     * Portál
     * @param position Pozícia portálu
     * @param map Mapa
     */
    public Portal(Position position, Map map) {
        super(new EImageList[] { EImageList.PORTAL }, ERenderLayer.PORTALS);
        super.getPosition().setPosition(position);
        this.map = map;
    }

    /**
     * @return Skupina portálu
     */
    public PortalGroup getPortalGroup() {
        return this.portalGroup;
    }

    /**
     * Nastaví portálovú skupinu
     * @param portalGroup Skupina
     */
    public void setPortalGroup(PortalGroup portalGroup) {
        this.portalGroup = portalGroup;
    }

    /**
     * Teleportne hráča na druhú stranu portálu
     * @param player Hráča
     * @param mapHandler Map Handler
     */
    public void teleport(Player player, MapHandler mapHandler) {
        if (this.status != null) {
            return;
        }

        ESoundList.playSound(ESoundList.PORTAL);
        Portal portal = this.portalGroup.getTheOtherPortal(this);
        mapHandler.changeMap(portal.getMap());
        player.getPosition().setPosition(portal.getPosition());

        Projectile.resetProjectiles();
    }

    /**
     * @return Vráti mapu, na ktorej sa portál nachádza
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Nastaví stav portálu
     * @param status Stav
     */
    public void setStatus(EPortalStatus status) {
        this.status = status;
    }

    /**
     * Stará sa o vykreslovanie portálu
     * @param g2d Plátno
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (this.status != null) {
            return;
        }

        g2d.drawImage(this.getImage(), (int)this.getPosition().getX() - 29 / 2, (int)this.getPosition().getY() - 126 / 2, 29, 126, null);
    }

    /**
     * @return Stav portálu
     */
    public EPortalStatus getStatus() {
        return this.status;
    }
}
