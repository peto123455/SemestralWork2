package sk.uniza.fri.essentials;

public class Vector {
    private double x;
    private double y;

    /**
     * Dvojrozmerný vektor
     * @param x Súradnica X
     * @param y Súradnica Y
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return Súradnica X
     */
    public double getX() {
        return this.x;
    }

    /**
     * Nastaví súradnicu X
     * @param x Súradnica X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return Súradnica Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Nastaví súradnicu Y
     * @param y Súradnica Y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Vynásobí vektor nejakou hodnotou
     * @param constant Hodnota
     */
    public void multiply(double constant) {
        this.x *= constant;
        this.y *= constant;
    }

    /**
     * @return Dĺžka vektora
     */
    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Normalizuje vekor (Nastaví na dĺžku 1)
     */
    public void normalize() {
        this.multiply(1.0 / this.length());
    }
}
