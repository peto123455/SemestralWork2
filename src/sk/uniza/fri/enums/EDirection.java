package sk.uniza.fri.enums;

import sk.uniza.fri.essentials.Position;

import java.awt.event.KeyEvent;

public enum EDirection {
    UP(new Position(0, -1), KeyEvent.VK_W),
    DOWN(new Position(0, 1), KeyEvent.VK_S),
    LEFT(new Position(-1, 0), KeyEvent.VK_A),
    RIGHT(new Position(1, 0), KeyEvent.VK_D);

    private Position pos;
    private int character;

    EDirection(Position pos, int character) {
        this.pos = pos;
        this.character = character;
    }

    public Position getPos() {
        return this.getPos(1);
    }

    public Position getPos(int i) {
        return new Position(this.pos.getCoordX() * i, this.pos.getCoordY() * i); //Posielam novú inštanciu Pos. aby náhodou nedošlo k jej zmene hodnôt
    }

    public static Position getPosByInt(int c) {
        return EDirection.getPosByInt(c, 1);
    }

    public static Position getPosByInt(int c, int i) {
        return EDirection.getDirByInt(c).getPos(i);
    }

    public static EDirection getDirByInt(int c) {
        for (EDirection eDirection : EDirection.values()) {
            if (eDirection.character == c) {
                return eDirection;
            }
        }
        return null;
    }
}
