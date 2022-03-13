package sk.uniza.fri.essentials;

import sk.uniza.fri.ui.GamePanel;

public class Position {
    private int[] coords;

    public Position(int x, int y) {
        this.coords = new int[2];

        this.coords[0] = x;
        this.coords[1] = y;
    }

    public Position() {
        this(0, 0);
    }

    public int getCoordX() {
        return this.coords[0];
    }

    public int getCoordY() {
        return this.coords[1];
    }

    public void setCoordX(int x) {
        this.coords[0] = x;
    }

    public void setCoordY(int y) {
        this.coords[1] = y;
    }

    public Position addPosition(Position pos) {
        this.coords[0] += pos.getCoordX();
        this.coords[1] += pos.getCoordY();
        return this;
    }

    public void setPosition(Position pos) {
        this.coords[0] = pos.getCoordX();
        this.coords[1] = pos.getCoordY();
    }

    public static Position getPositionRelativeToGrid(Position position) {
        return new Position(position.getCoordX() / GamePanel.TILE_SIZE, position.getCoordY() / GamePanel.TILE_SIZE);
    }

    public static double getDistance(Position pos1, Position pos2) {
        double x = Math.abs((double)pos1.getCoordX() - (double)pos2.getCoordX());
        double y = Math.abs((double)pos1.getCoordY() - (double)pos2.getCoordY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
