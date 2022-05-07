package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EImageList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {

    /**
     * Samotné hlavné okno hry
     */
    public GameFrame() {
        super.setTitle("Semestralka");
        super.setResizable(false);
        super.setIconImage(EImageList.KNIGHT.getImage());
        super.setVisible(true);

        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(GameFrame.this, "Do you really want to quit the game ?", "Semestralka - Game exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    GameFrame.super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    return;
                }
                GameFrame.super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }
}
