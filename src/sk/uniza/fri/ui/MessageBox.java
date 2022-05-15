package sk.uniza.fri.ui;

import sk.uniza.fri.enums.EFontList;
import sk.uniza.fri.enums.ESoundList;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MessageBox {
    private static ArrayList<MessageBox> messageBoxes = new ArrayList<>();

    private long deleteAt;
    private int duration;
    private String text;

    /**
     * Správy, ktoré sa hráčovi zobrazujú (Ako napríklad rozhovor s NPC).
     * @param text Text správy
     * @param duration dĺžka zobrazenia
     */
    public MessageBox(String text, int duration) {
        this.duration = duration;
        this.deleteAt = System.currentTimeMillis() + duration;
        this.text = text;
        ESoundList.playSound(ESoundList.MESSAGE);
        MessageBox.messageBoxes.add(this);
    }

    /**
     * Kontroluje, či už uplynul čas zobrazenia správy, ak áno, zmaže ju;
     */
    public void update() {
        if (this.deleteAt <= System.currentTimeMillis()) {
            MessageBox.messageBoxes.remove(this);
        }
    }

    /**
     * Vykreslenie samotnej správy
     * @param g2d Plátno
     * @param offset O koľko má byť okno posunuté smerom nadol (Ak sa zobrazuje viacero správ).
     * @return Vracia potrebný offset pre dalšiu správu.
     */
    public int draw(Graphics2D g2d, int offset) {
        //String[] strings = this.text.split("\n");

        ArrayList<String> strings = new ArrayList<>();

        int counter = 0;
        String currentString = "";
        for (String string : this.text.split(" ")) {
            if (counter > 25) {
                counter = 0;
                strings.add(currentString);
                currentString = "";
            }
            currentString += " " + string;
            counter += string.length() + 1; //dĺžka + medzera
        }
        strings.add(currentString);

        g2d.setFont(EFontList.DIALOG.getFont());

        g2d.setColor(Color.BLACK);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(950, offset, 280, g2d.getFontMetrics().getHeight() * strings.size() + 50, 10, 10);


        g2d.setColor(Color.BLACK);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(950, offset, 280, g2d.getFontMetrics().getHeight() * strings.size() + 50, 10, 10);
        g2d.setStroke(tmp);

        //Rozdelenie riadkov
        int y = offset;
        int added = g2d.getFontMetrics().getHeight();

        for (String string : strings) {
            added += g2d.getFontMetrics().getHeight();
            g2d.drawString(string, 975, y + added);
        }

        return 25 * strings.size() + 75;
    }

    /**
     * Kontroluje stav všetkých správ
     */
    public static void checkMessages() {
        for (int i = 0; i < MessageBox.messageBoxes.size(); ++i) {
            MessageBox.messageBoxes.get(i).update();
        }
    }

    /**
     * Stará sa o zobrazenie všetkcýh správ
     * @param g2d Plátno
     */
    public static void drawMessages(Graphics2D g2d) {
        int y = GamePanel.HEIGHT / 8;
        for (MessageBox message : MessageBox.messageBoxes) {
            y += message.draw(g2d, y);
        }
    }

    /**
     * Zobrazí správu s časovým odstupom.
     * @param message Text Správy
     * @param time Koľko ju má zobrazovať
     * @param delay O koľko ju má zobraziť
     */
    public static void shceduleMessage(String message, int time, int delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new MessageBox(message, time);
            }
        }, delay);
    }
}
