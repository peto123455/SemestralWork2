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

    /**
     * Enum smerov
     * @param pos Smerodajná pozícia
     * @param character Reprezentácia smeru znakom (W,A,S,D) pre hráča
     */
    EDirection(Position pos, int character) {
        this.pos = pos;
        this.character = character;
    }

    /**
     * @return Vráti smerodajnú pozíciu s dĺžkou 1
     */
    public Position getPos() {
        return this.getPos(1);
    }

    /**
     *
     * @param i Prenásobí smerodajnú pozíciu danou hodnotou
     * @return Vráti smerodajnú pozíciu s dĺžkou i
     */
    public Position getPos(int i) {
        return new Position((int)this.pos.getX() * i, (int)this.pos.getY() * i); //Posielam novú inštanciu Pos. aby náhodou nedošlo k jej zmene hodnôt
    }

    /**
     * Vráti smerodajnú pozíciu s dĺžkou 1 na základe znaku
     * @param c Číslo znaku
     * @return Vráti smerodajnú pozíciu s dĺžkou 1
     */
    public static Position getPosByInt(int c) {
        return EDirection.getPosByInt(c, 1);
    }

    /**
     * Vráti smerodajnú pozíciu s dĺžkou i na základe znaku
     * @param c Číslo znaku
     * @param i Prenásobí smerodajnú pozíciu danou hodnotou
     * @return Vráti smerodajnú pozíciu s dĺžkou i
     */
    public static Position getPosByInt(int c, int i) {
        return EDirection.getDirByInt(c).getPos(i);
    }

    /**
     * Vráti smer na základe znaku
     * @param c Číslo znaku
     * @return Smer
     */
    public static EDirection getDirByInt(int c) {
        for (EDirection eDirection : EDirection.values()) {
            if (eDirection.character == c) {
                return eDirection;
            }
        }
        return null;
    }
}
