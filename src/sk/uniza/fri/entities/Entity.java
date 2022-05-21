package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameThread;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private BufferedImage[] images;
    private ERenderLayer renderLayer;

    private final Position position;
    private Position toPos;
    private int speed;

    /**
     * Vytvorí entitu
     * @param images Obrázky
     */
    public Entity(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }

        this.renderLayer = ERenderLayer.DEFAULT;
        this.speed = 4;
        this.position = new Position();
    }

    /**
     * Vytvorí entitu a pridelí jej vykreslovaciu vrstvu
     * @param images Obrázky
     * @param renderLayer Vrstva
     */
    public Entity(EImageList[] images, ERenderLayer renderLayer) {
        this(images);
        this.renderLayer = renderLayer;
    }

    /**
     * Vráti obrázok na vykreslenie
     * @return Obrázok
     */
    public BufferedImage getImage() {
        return this.getImage(0);
    }

    /**
     * Vráti obrázok na vykreslenie s id
     * @param i ID
     * @return Obrázok
     */
    public BufferedImage getImage(int i) {
        if (i < 0 || i >= this.images.length) {
            return null;
        }

        return this.images[i];
    }

    /**
     * @return Vráti pozíciu objektu
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Skontroluje, či sa nachádza v blízkosti zadanej entity
     * @param entity Entita
     * @param distance Max. vzdialenosť
     * @return Je v dosahu
     */
    public boolean isNearEntity(Entity entity, double distance) {
        return Position.getDistance(this.getPosition(), entity.getPosition()) <= distance;
    }

    /**
     * Nastaví obrázky
     * @param images Zoznam obrázkov
     */
    public void changeImages(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }
    }

    /**
     * Stará sa o vykresľovanie entity
     * @param g2d Plátno
     */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.getImage(), (int)this.getPosition().getX() - GamePanel.TILE_SIZE / 2, (int)this.getPosition().getY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    /**
     * Entita dostane pokyn ísť na danú pozíciu
     * @param toPos Pozícia
     */
    public void goToPos(Position toPos) {
        this.toPos = toPos;
    }

    /**
     * Stará sa o aktualizovanie Entity
     * @param game Hra
     * @return Či update prebehol
     */
    public boolean update(Game game) {
        this.updatePos(game);
        return true;
    }

    /**
     * @return Vráti vykresľujúcu vrstvu
     */
    public ERenderLayer getRenderLayer() {
        return this.renderLayer;
    }

    /**
     * Nastaví rýchlosť
     * @param speed Rýchlosť
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return Vráti zoznam obrázkov
     */
    protected BufferedImage[] getImages() {
        return this.images;
    }

    /**
     * Aktualizuje pozíciu Entity
     * @param game hra
     * @return Ktorým smerom sa entita hýbe
     */
    protected EDirection updatePos(Game game) {
        return this.updatePos(game, this.toPos);
    }

    /**
     * Aktualizuje pozíciu Entity
     * @param game hra
     * @param position Pozícia kam má ísť
     * @return Ktorým smerom sa entita hýbe
     */
    protected EDirection updatePos(Game game, Position position) {
        if (position == null) {
            return null;
        }

        Vector vector = new Vector(position.getX() - this.position.getX(), position.getY() - this.position.getY());
        //Obmedzenie maximálnej prejdenej vzialenosti na 4px
        if (vector.length() > 50 * this.speed * GameThread.getInstance().getDeltaTime()) {
            vector.normalize();
            vector.multiply(50 * this.speed * GameThread.getInstance().getDeltaTime());
        }

        Position pos = new Position(vector.getX(), vector.getY());

        if (pos.getX() != 0 || pos.getY() != 0) {
            this.move(new Position(pos.getX(), 0), game.getMapHandler());
            this.move(new Position(0, pos.getY()), game.getMapHandler());

            if (pos.getX() < 0) {
                return EDirection.LEFT;
            } else if (pos.getX() > 0) {
                return EDirection.RIGHT;
            }
        } else {
            this.toPos = null;
        }
        return null;
    }

    /**
     * Stará sa o samotný pohyb entity
     * @param byPos O akú pozíciu sa má posunúť
     * @param mapHandler Map Handler
     */
    protected void move(Position byPos, MapHandler mapHandler) {
        //byPos.multiply(GameThread.getInstance().getDeltaTime() * 50);

        //Systém kolízií
        Position futurePosition = new Position().addPosition(this.getPosition()).addPosition(byPos);

        if (this.isCollision(futurePosition, mapHandler.getMap())) {
            return;
        }

        //Pohyb
        this.getPosition().addPosition(byPos);
    }

    /**
     * Či koliduje
     * @param futurePosition Budúca pozícia
     * @param map Mapa
     * @return Koliduje
     */
    protected boolean isCollision(Position futurePosition, Map map) {
        futurePosition = Position.getPositionRelativeToGrid(futurePosition);
        GameTile tile = map.getTile((int)futurePosition.getX(), (int)futurePosition.getY());
        return tile != null && tile.hasCollision();
    }

    /**
     * @return Vráti pozíciu kam sa Entita snaží dostať
     */
    protected Position getToPos() {
        return this.toPos;
    }

    /**
     * Nastaví vykresľujúcu vrstvu
     * @param renderLayer Vykresľujúca vrstva
     */
    protected void setRenderLayer(ERenderLayer renderLayer) {
        this.renderLayer = renderLayer;
    }
}
