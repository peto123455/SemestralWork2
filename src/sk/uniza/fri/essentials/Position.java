package sk.uniza.fri.essentials;

import sk.uniza.fri.ui.GamePanel;

public class Position {
    private double[] coords;

    public Position(double x, double y) {
        this.coords = new double[2];

        this.coords[0] = x;
        this.coords[1] = y;
    }

    public Position() {
        this(0, 0);
    }

    public int getIntCoordX() {
        return (int)this.coords[0];
    }

    public int getIntCoordY() {
        return (int)this.coords[1];
    }

    public double getCoordX() {
        return this.coords[0];
    }

    public double getCoordY() {
        return this.coords[1];
    }

    public void setCoordX(int x) {
        this.coords[0] = x;
    }

    public void setCoordY(int y) {
        this.coords[1] = y;
    }

    public void multiply(double constant) {
        this.coords[0] *= constant;
        this.coords[1] *= constant;
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
        return new Position(position.getIntCoordX() / GamePanel.TILE_SIZE, position.getIntCoordY() / GamePanel.TILE_SIZE);
    }

    public static double getDistance(Position pos1, Position pos2) {
        double x = Math.abs((double)pos1.getIntCoordX() - (double)pos2.getIntCoordX());
        double y = Math.abs((double)pos1.getIntCoordY() - (double)pos2.getIntCoordY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double length() {
        return Math.sqrt(Math.pow(this.coords[0], 2) + Math.pow(this.coords[1], 2));
    }
}
