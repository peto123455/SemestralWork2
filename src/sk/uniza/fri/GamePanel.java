package sk.uniza.fri;

import javax.swing.JPanel;
import java.awt.*;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class GamePanel extends JPanel {
    private int width = 1280;
    private int height = 720;

    private Map map;

    public GamePanel() {
        this.map = new Map(this.width / 3, this.height / 3);

        super.setPreferredSize(new Dimension(this.width, this.height));
        super.setDoubleBuffered(true);
        super.setBackground(Color.BLACK);

        this.map.setTile(1, 0, ETileList.WALL1);
        this.map.setTile(2, 0, ETileList.WALL1);
        this.map.setTile(3, 0, ETileList.WALL1);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for (int i = 0; i < this.map.getSizeY() ; ++i) {
            for (int j = 0; j < this.map.getSizeX(); ++j) {
                if (this.map.getTile(j, i) == null) {
                    continue;
                }

                g2d.drawImage(this.map.getTile(j, i).getImage(), j * 42, i * 42, 42, 42, null);

            }
        }
    }
}
