package interfaces.map.tile;

import java.awt.Graphics2D;

import interfaces.map.Map;

public class TileManager {
    
    protected Tile[] tile;
    Map map;

    public TileManager(Map map) {
        this.map = map;
        tile = new Tile[map.map_x * map.map_y];
    }

    public void drawTiles(Graphics2D g2d) {
        for (int y = 0; y < map.map_y; y++) {
            for (int x = 0; x < map.map_x; x++) {
                if (map.map_data[x][y] == 0) {
                    g2d.setColor(java.awt.Color.green);
                } else {
                    g2d.setColor(java.awt.Color.blue);
                }
                g2d.fillRect(x * map.tile_size, y * map.tile_size, map.tile_size, map.tile_size);
            }
        }
    }
}
