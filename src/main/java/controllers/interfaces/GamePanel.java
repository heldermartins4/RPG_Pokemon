package controllers.interfaces;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controllers.game.controls.KeyHandler;
import controllers.game.entity.Player;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    
    final int scale_x = 16;
    final int scale_y = 12;
    final int screen_width = scale_x * 48;
    final int screen_height = scale_y * 48;

    public int tile_size = 32;

    int player_x = 100;
    int player_y = 100;
    int player_speed = 4;

    int fps = 60;
    private long lastUpdateTime = System.nanoTime();

    public KeyHandler key = new KeyHandler();

    Thread game_thread;
    Player player = new Player(this, key);
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        // this.setBackground(Color.black);

        this.setDoubleBuffered(true);
        
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
            delta += (now - lastUpdateTime) / draw_interval;
            lastUpdateTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();

            try {
                double remaining_time = (lastUpdateTime - now + draw_interval) / 1_000_000_000;
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

        BufferedImage img = null; // set default image

        try {
            img = ImageIO.read(getClass().getResource("/assets/bg-room.jpg"));
            g.drawImage(img, 0, 0, screen_width, screen_height, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Graphics2D g2d = (Graphics2D) g;
        
        player.draw(g2d);

        g2d.dispose();
    }
}