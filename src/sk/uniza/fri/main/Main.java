package sk.uniza.fri.main;

import sk.uniza.fri.enums.EGameStatus;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.GameAlreadyRunningException;
import sk.uniza.fri.ui.GameFrame;
import sk.uniza.fri.ui.GamePanel;
import sk.uniza.fri.ui.MenuPanel;

import javax.swing.JOptionPane;

public class Main {

    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private Game game;
    private GameFrame gameWindow;
    private EGameStatus status;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        ESoundList.playSound(ESoundList.MUSIC, true);
        this.menuPanel = new MenuPanel();
        this.gamePanel = new GamePanel();
        this.game = new Game(this.gamePanel);
        this.gameWindow = new GameFrame();
        this.status = EGameStatus.MENU;

        this.registerListeners(); //Registruje akcie na tlačiadlá

        this.gameWindow.add(this.menuPanel);
        this.gameWindow.pack();

        this.gameWindow.setLocationRelativeTo(null);
    }

    private void startGame() throws GameAlreadyRunningException {
        if (this.status != EGameStatus.MENU) {
            throw new GameAlreadyRunningException();
        }

        //Zmena panelov
        this.gameWindow.remove(this.menuPanel);
        this.gameWindow.add(this.gamePanel);

        //Spustenie hry
        this.game.startGame();

        this.gameWindow.pack();
        this.gamePanel.requestFocus();

        this.gameWindow.setLocationRelativeTo(null);
    }

    private void registerListeners() {
        this.menuPanel.registerAction(e -> this.startGame(), 0); //Zavolá startGame() pri stlačení tlačiala
        this.menuPanel.registerAction(e -> JOptionPane.showMessageDialog(null, "W,A,S,D - Movement\nE - Action key\nQ - Use health potion\nLMB / K - Attack"), 1);
        this.menuPanel.registerAction(e -> System.exit(0), 2); //Vypne hru
    }
}
