package sk.uniza.fri.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
        String[] strings = this.text.split("\n");

        g2d.setColor(Color.WHITE);
        g2d.fillRect(50, 70, 300, 25 * strings.length);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));

        //Rozdelenie riadkov
        int y = 70;
        for (String string : strings) {
            y += g2d.getFontMetrics().getHeight();
            g2d.drawString(string, 53, y);
        }
    }

    public static ArrayList<MessageBox> getMessageBoxes() {
        return messageBoxes;
    }
}
