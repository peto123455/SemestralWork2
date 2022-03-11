package sk.uniza.fri;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        JFrame gameWindow = new GameFrame(gamePanel);
    }
}
