package sk.uniza.fri.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton[] buttons;

    public MenuPanel() {
        this.buttons = new JButton[2];

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(250, 400));
        this.buttons[0] = new JButton("Start Game");
        this.buttons[1] = new JButton("Exit");

        for (JButton button : this.buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMargin(new Insets(5, 10, 5, 10));
            super.add(button);
        }
    }

    public void registerAction(ActionListener a, int id) {
        this.buttons[id].addActionListener(a);
    }
}
