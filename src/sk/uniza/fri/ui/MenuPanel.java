package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EImageList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private ArrayList<JButton> buttons;

    /**
     * Menu, do ktorého sa používateľ dostane pri zapnutí hry.
     */
    public MenuPanel() {
        this.buttons = new ArrayList<>();

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(250, 250));
        this.buttons.add(new JButton("Start game"));
        this.buttons.add(new JButton("Help"));
        this.buttons.add(new JButton("Exit"));

        for (JButton button : this.buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMargin(new Insets(5, 10, 5, 10));
            button.setBackground(new Color(140, 140, 140));
            button.setFont(new Font("Monospaced", Font.BOLD, 12));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            super.add(Box.createHorizontalStrut(1));
            super.add(button);
        }
        super.add(Box.createHorizontalStrut(1));
    }

    /**
     * Vykresluje obrázok na pozadí.
     * @param g Plátno, na ktoré vykreslí obrázok
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(EImageList.MENU.getImage(), 0, 0, null);
    }

    /**
     * Zaregistruje akciu na tlačidlo v menu.
     * @param a Akcia
     * @param id Id tlačidla
     */
    public void registerAction(ActionListener a, int id) {
        this.buttons.get(id).addActionListener(a);
    }
}
