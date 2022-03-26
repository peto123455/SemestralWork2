package sk.uniza.fri.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    public int draw(Graphics2D g2d, int offset) {
        String[] strings = this.text.split("\n");

        g2d.setColor(Color.WHITE);
        g2d.fillRect(950, offset, 260, 25 * strings.length + 50);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));

        //Rozdelenie riadkov
        int y = offset;
        int added = 25;

        for (String string : strings) {
            added += g2d.getFontMetrics().getHeight();
            g2d.drawString(string, 975, y + added);
        }

        return 25 * strings.length + 75;
    }

    public static ArrayList<MessageBox> getMessageBoxes() {
        return messageBoxes;
    }

    public static void shceduleMessage(String message, int time, int delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new MessageBox(message, time);
            }
        }, delay);
    }
}
