package sk.uniza.fri;

import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new GamePanel();

        gameWindow.setTitle("Semestralka");
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameWindow.add(gamePanel);
        gameWindow.pack(); //Prispôsobí veľkosť okna
    }
}
