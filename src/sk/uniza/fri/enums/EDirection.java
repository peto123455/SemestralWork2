package sk.uniza.fri.enums;

import sk.uniza.fri.essentials.Position;

public enum EDirection {
    UP(new Position(0, -1), 'w'),
    DOWN(new Position(0, 1), 's'),
    LEFT(new Position(-1, 0), 'a'),
    RIGHT(new Position(1, 0), 'd');

    private Position pos;
    private char character;

    EDirection(Position pos, char character) {
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
        return EDirection.getPosByChar(c, 1);
    }

    public static Position getPosByChar(char c, int i) {
        return EDirection.getDirByChar(c).getPos(i);
    }

    public static EDirection getDirByChar(char c) {
        for (EDirection eDirection : EDirection.values()) {
            if (eDirection.character == c) {
                return eDirection;
            }
        }
        return null;
    }
}
