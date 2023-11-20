package controllers.interfaces.map;

import java.awt.Graphics2D;

import controllers.interfaces.GamePanel;
import java.awt.Color;

public class TileManager extends Map {
    
    protected Tile[] tile;
    GamePanel gp;

    public TileManager(int map_width, int map_height, GamePanel gp) {
        super(map_width, map_height);
        this.gp = gp;
        tile = new Tile[map_width * map_height];
    }

    public void drawTiles(Graphics2D g2d) {

        int tile_x = 0;
        int tile_y = 0;

        boolean tile_index = false;

        for (int i = 0; i < tile.length; i++) {

            if (tile_x >= gp.screen_width) {
                tile_x = 0;
                tile_y += gp.tile_size;

                tile_index = !tile_index;
            }

            if ((i % 2) == 0) {
                if (tile_index) {
                    float[] hsb = Color.RGBtoHSB(18, 18, 18, null);
                    int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    g2d.setColor(new Color(rgb));
                } else {
                    float[] hsb = Color.RGBtoHSB(39, 39, 39, null);
                    int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    g2d.setColor(new Color(rgb));
                }
            } else {
                if (tile_index) {
                    float[] hsb = Color.RGBtoHSB(39, 39, 39, null);
                    int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    g2d.setColor(new Color(rgb));
                } else {
                    float[] hsb = Color.RGBtoHSB(18, 18, 18, null);
                    int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    g2d.setColor(new Color(rgb));
                }
            }

            g2d.fillRect(tile_x, tile_y, gp.tile_size, gp.tile_size);
            
            tile_x += gp.tile_size;

            tile[i] = new Tile();
            if (tile[i] != null)
                g2d.drawImage(tile[i].tile_img, gp.max_screen_row, gp.max_screen_col, gp);
        }
    }
}
