package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EImageList;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        super.setTitle("Semestralka");
        super.setResizable(false);
        super.setIconImage(EImageList.KNIGHT.getImage());
        super.setVisible(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
