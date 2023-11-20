package controllers.interfaces;

import javax.swing.JPanel;

import controllers.game.controls.KeyHandler;
import controllers.game.entity.Player;
import controllers.interfaces.map.TileManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    
    final int scale = 3;

    public int max_screen_col = 16;
    public int max_screen_row = 12;

    private int original_tile_size = 16;
    public int tile_size = original_tile_size * scale;  

    public int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;
    // final int screen_width = 768;
    // final int screen_height = 576;

    TileManager tm;

    int player_x = 0;
    int player_y = 0;
    int player_speed = 4;

    int fps = 60;
    private long last_update_time = System.nanoTime();

    public KeyHandler key = new KeyHandler();

    Thread game_thread;
    Player player = new Player(this, key);
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        // this.setBackground(Color.black);

        this.setDoubleBuffered(true);

        tm = new TileManager(max_screen_row, max_screen_col, this);
        
        player.setDefaultValues(player_x, player_y, tile_size, tile_size, player_speed);
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    // @Override
    // public void run() {

    //     double draw_interval = 1_000_000_000 / fps;
    //     double next_draw_time = System.nanoTime() + draw_interval;

    //     while (game_thread != null) {
            
    //         update();

    //         repaint();

    //         try {
    //             double remaining_time = (next_draw_time - System.nanoTime());

    //             remaining_time /= 1_000_000;

    //             remaining_time = remaining_time < 0 ? 0 : remaining_time;

    //             Thread.sleep((long) (remaining_time / 1000000));

    //             next_draw_time += draw_interval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    @Override
    public void run() {
        double delta = 0;
        double draw_interval = 1_000_000_000 / fps;

        while (game_thread != null) {
            long now = System.nanoTime();
            delta += (now - last_update_time) / draw_interval;
            last_update_time = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();

            try {
                double remaining_time = (last_update_time - now + draw_interval) / 1_000_000_000;
                remaining_time = remaining_time < 0 ? 0 : remaining_time;

                Thread.sleep((long)remaining_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        
        player.update();
    }

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        // BufferedImage img = null; // set default image

        // try {
        //     img = ImageIO.read(getClass().getResource("/assets/bg-test.jpg"));
        //     g.drawImage(img, 0, 0, screen_width, screen_height, null);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        Graphics2D g2d = (Graphics2D) g;

        tm.drawTiles(g2d);
        
        player.draw(g2d);

        g2d.dispose();
    }
}