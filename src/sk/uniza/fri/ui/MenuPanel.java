package sk.uniza.fri.ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private ArrayList<JButton> buttons;

    public MenuPanel() {
        this.buttons = new ArrayList<>();

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(250, 250));
        this.buttons.add(new JButton("Začať hru"));
        this.buttons.add(new JButton("Pomoc"));
        this.buttons.add(new JButton("Koniec"));

        for (JButton button : this.buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMargin(new Insets(5, 10, 5, 10));
            super.add(Box.createHorizontalStrut(1));
            super.add(button);
        }
        super.add(Box.createHorizontalStrut(1));
    }

    public void registerAction(ActionListener a, int id) {
        this.buttons.get(id).addActionListener(a);
    }
}
