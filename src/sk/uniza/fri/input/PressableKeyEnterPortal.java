package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

public class PressableKeyEnterPortal extends PressableKey {

    @Override
    public void action(Game game) {
        game.enterPortal();
    }
}
