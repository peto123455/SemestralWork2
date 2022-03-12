package sk.uniza.fri;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class Map {
    private GameTile[][] mapLayout;

    public Map(int sizeX, int sizeY) {
        this.mapLayout = new GameTile[sizeX][sizeY];
    }

    public void setTile(int x, int y, EImageList tile) {
        if (tile == null) {
            this.mapLayout[x][y] = null;
            return;
        }

        this.mapLayout[x][y] = new GameTile(tile.getImage());
    }

    public GameTile getTile(int x, int y) {
        return mapLayout[x][y];
    }

    public int getSizeX() {
        return this.mapLayout.length;
    }

    public int getSizeY() {
        return this.mapLayout[0].length;
    }
}
