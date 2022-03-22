package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

public class PressableKeyHealthPotion extends PressableKey {

    @Override
    public void action(Game game) {
        game.useHealthPotion();
    }
}
