package sk.uniza.fri;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
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

    public void addPosition(Position pos) {
        this.coords[0] += pos.getCoordX();
        this.coords[1] += pos.getCoordY();
    }

    public void setPosition(Position pos) {
        this.coords[0] = pos.getCoordX();
        this.coords[1] = pos.getCoordY();
    }
}
