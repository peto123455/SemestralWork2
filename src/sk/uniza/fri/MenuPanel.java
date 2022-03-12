package sk.uniza.fri;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton[] buttons;

    public MenuPanel() {
        this.buttons = new JButton[2];

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(500, 500));
        this.buttons[0] = new JButton("Start Game");
        this.buttons[1] = new JButton("Exit");

        for (JButton button : this.buttons) {
            super.add(button);
        }
    }

    public void registerAction(ActionListener a, int id) {
        this.buttons[id].addActionListener(a);
    }
}
