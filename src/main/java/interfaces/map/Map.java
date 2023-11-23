package interfaces.map;

import interfaces.map.data.MapDataManager;
import interfaces.map.tile.TileManager;

import java.awt.Graphics2D;


public class Map extends MapDataManager {

    TileManager tm;

    final int scale = 3;

    public int max_screen_col = 16;
    public int max_screen_row = 12;

    private int original_tile_size = 16;
    public int tile_size = original_tile_size * scale;  

    public int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;

    public Map(String mapName) {

        super(mapName);
        tm = new TileManager(this); // Passa a instância atual de Map para TileManager
    }

    public void loadMap(Graphics2D g2d) {

        tm.drawTiles(g2d);
    }
}
