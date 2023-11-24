package interfaces.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import interfaces.map.data.MapDataManager;

public class Map extends MapDataManager {

    TileManager tm;

    final int scale = 3;

    public int max_screen_col = 16;
    public int max_screen_row = 12;

    private int original_tile_size = 16;
    public int tile_size = original_tile_size * scale;  

    public int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;
    public BufferedImage map;

    public Map(String mapName) {

        super(mapName);
        tm = new TileManager(this); // Passa a instância atual de Map para TileManager
    }

    public void loadMap(Graphics2D g2d) {
        try {
            map = ImageIO.read(getClass().getResource("/sprites/map/map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        };

        g2d.drawImage(map, 0, 0, null);
    }
}
