package sk.uniza.fri;

public enum Direction {
    UP(new Position(0, -4), 'w'),
    DOWN(new Position(0, 4), 's'),
    LEFT(new Position(-4, 0), 'a'),
    Right(new Position(4, 0), 'd');

    private Position pos;
    private char character;

    Direction(Position pos, char character) {
        this.pos = pos;
        this.character = character;
    }

    public Position getPos() {
        return new Position(this.pos.getCoordX(), this.pos.getCoordY()); //Posielam novú inštanciu Pos. aby náhodou nedošlo k jej zmene hodnôt
    }

    public static Position getPosByChar(char c) {
        for (Direction direction : Direction.values()) {
            if (direction.character == c) {
                return direction.getPos();
            }
        }
        return null;
    }
}
