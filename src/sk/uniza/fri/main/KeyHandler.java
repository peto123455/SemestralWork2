package sk.uniza.fri.main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class KeyHandler {
    private HashMap<Character, Boolean> pressed;
    private Game game;

    public KeyHandler(Game panel) {
        this.pressed = new HashMap<Character, Boolean>();


        this.game = game;
        this.registerKeys();
    }

    private void registerKeys() {
        this.pressed.put('w', false);
        this.pressed.put('a', false);
        this.pressed.put('s', false);
        this.pressed.put('d', false);
    }

    public ArrayList<Character> getPressedKeys() {
        ArrayList<Character> list = new ArrayList<>();
        this.pressed.forEach((kluc, hodnota) -> {
            if (hodnota) {
                list.add(kluc);
            }
        });
        return list;
    }

    public void keyUpdate(char c, boolean pressed) {
        if (this.pressed.containsKey(c)) {
            this.pressed.put(c, pressed);
        }
    }
}
