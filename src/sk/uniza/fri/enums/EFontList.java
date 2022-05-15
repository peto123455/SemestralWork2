package sk.uniza.fri.enums;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public enum EFontList {
    DIALOG("/fonts/dialog.ttf", 18),
    RPG("/fonts/rpg.ttf", 35);

    private Font font;

    EFontList(String path, int defaultSize) {
        try {
            InputStream is = this.getClass().getResourceAsStream(path);
            this.font = Font.createFont(Font.TRUETYPE_FONT, is);
            this.font = this.font.deriveFont(Font.PLAIN, defaultSize);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return this.font;
    }
}
