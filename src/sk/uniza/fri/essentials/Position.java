package sk.uniza.fri.essentials;

import sk.uniza.fri.ui.GamePanel;

public class Position extends Vector {

    public Position(double x, double y) {
        super(x, y);
    }

    public Position() {
        this(0, 0);
    }

    public Position addPosition(Position pos) {
        super.setX(super.getX() + pos.getX());
        super.setY(super.getY() + pos.getY());
        return this;
    }

    public void setPosition(Position pos) {
        super.setX(pos.getX());
        super.setY(pos.getY());
    }

    public static Position getPositionRelativeToGrid(Position position) {
        return new Position(position.getX() / GamePanel.TILE_SIZE, position.getY() / GamePanel.TILE_SIZE);
    }

    public static double getDistance(Position pos1, Position pos2) {
        double x = Math.abs(pos1.getX() - pos2.getX());
        double y = Math.abs(pos1.getY() - pos2.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double length() {
        return Math.sqrt(Math.pow(super.getX(), 2) + Math.pow(super.getY(), 2));
    }
}
