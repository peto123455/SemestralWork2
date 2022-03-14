package sk.uniza.fri.ui;

import java.awt.*;
import java.util.ArrayList;

public class MessageBox {
    private static ArrayList<MessageBox> messageBoxes = new ArrayList<>();

    private long deleteAt;
    private int duration;
    private String text;

    public MessageBox(String text, int duration) {
        this.duration = duration;
        this.deleteAt = System.currentTimeMillis() + duration;
        this.text = text;
        MessageBox.messageBoxes.add(this);
    }

    public void update() {
        if (this.deleteAt <= System.currentTimeMillis()) {
            MessageBox.messageBoxes.remove(this);
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(50, 70, 100, 50);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString(this.text, 55, 90);
    }

    public static ArrayList<MessageBox> getMessageBoxes() {
        return messageBoxes;
    }
}
