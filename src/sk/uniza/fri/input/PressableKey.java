package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

public abstract class PressableKey {
    private boolean pressed;

    /**
     * Používa sa na klávesi, ktorých akciu cheme vykonať iba 1x pri stlačení.
     */
    public PressableKey() {
        this.pressed = false;
    }

    /**
     * Či je klávesa stlačená
     * @return
     */
    public boolean isPressed() {
        return this.pressed;
    }

    /**
     * Nastaví jej stav
     * @param pressed
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Akcia klávesi
     * @param game Hra
     */
    public abstract void action(Game game);
}
