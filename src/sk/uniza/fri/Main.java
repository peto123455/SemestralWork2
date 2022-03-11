package sk.uniza.fri;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        Game game = new Game(gamePanel);
        JFrame gameWindow = new GameFrame(gamePanel, game);
    }
}
