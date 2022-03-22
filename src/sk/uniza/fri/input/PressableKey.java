package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

public abstract class PressableKey {
    private boolean pressed;

    public PressableKey() {
        this.pressed = false;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public abstract void action(Game game);
}
