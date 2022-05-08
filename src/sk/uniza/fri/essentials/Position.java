package sk.uniza.fri.essentials;

import sk.uniza.fri.ui.GamePanel;

public class Position extends Vector {

    /**
     * Pozícia entity
     * @param x Súradnica X
     * @param y Súradnica Y
     */
    public Position(double x, double y) {
        super(x, y);
    }

    public Position() {
        this(0, 0);
    }

    /**
     * Pripočíta pozíciu k danej pozícií
     * @param pos Pozícia na pripočítanie
     * @return Vráti pozíciu
     */
    public Position addPosition(Position pos) {
        super.setX(super.getX() + pos.getX());
        super.setY(super.getY() + pos.getY());
        return this;
    }

    /**
     * Nastaví pozíciu na danú pozíciu
     * @param pos
     */
    public void setPosition(Position pos) {
        super.setX(pos.getX());
        super.setY(pos.getY());
    }

    /**
     * Prevedie pozíciu na pozíciu v gride
     * @param position Pozícia hráča
     * @return Pozícia v gride
     */
    public static Position getPositionRelativeToGrid(Position position) {
        return new Position(position.getX() / GamePanel.TILE_SIZE, position.getY() / GamePanel.TILE_SIZE);
    }

    /**
     * Vráti dĺžku vzdialenosti medzi dvoma bodmi
     * @param pos1
     * @param pos2
     * @return Dĺžka
     */
    public static double getDistance(Position pos1, Position pos2) {
        double x = Math.abs(pos1.getX() - pos2.getX());
        double y = Math.abs(pos1.getY() - pos2.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
