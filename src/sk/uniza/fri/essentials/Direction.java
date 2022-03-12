package sk.uniza.fri.essentials;

public enum Direction {
    UP(new Position(0, -1), 'w'),
    DOWN(new Position(0, 1), 's'),
    LEFT(new Position(-1, 0), 'a'),
    Right(new Position(1, 0), 'd');

    private Position pos;
    private char character;

    Direction(Position pos, char character) {
        this.pos = pos;
        this.character = character;
    }

    public Position getPos() {
        return this.getPos(1);
    }

    public Position getPos(int i) {
        return new Position(this.pos.getCoordX() * i, this.pos.getCoordY() * i); //Posielam novú inštanciu Pos. aby náhodou nedošlo k jej zmene hodnôt
    }

    public static Position getPosByChar(char c) {
        return Direction.getPosByChar(c, 1);
    }

    public static Position getPosByChar(char c, int i) {
        return Direction.getDirByChar(c).getPos(i);
    }

    public static Direction getDirByChar(char c) {
        for (Direction direction : Direction.values()) {
            if (direction.character == c) {
                return direction;
            }
        }
        return null;
    }
}
