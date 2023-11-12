package controllers.interfaces;

import javax.swing.JPanel;

import controllers.game.controls.KeyHandler;
import controllers.game.entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    
    final int scale_x = 16;
    final int scale_y = 12;
    final int screen_width = scale_x * 32;
    final int screen_height = scale_y * 32;

    public int tile_size = 32;

    int player_x = 100;
    int player_y = 100;
    int player_speed = 1;

    int fps = 60;

    public KeyHandler key = new KeyHandler();

    Thread game_thread;
    Player player = new Player(this, key);
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.black);

        this.setDoubleBuffered(true);
        
        player.setDefaultValues(player_x, player_y, tile_size, tile_size, player_speed);
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {

        double draw_interval = 1000000000 / fps; // 0.01666 seconds
        double next_draw_time = System.nanoTime() + draw_interval;

        while (game_thread != null) {
            
            update();
            repaint();

            try {
                double remaining_time = next_draw_time - System.nanoTime();

                remaining_time /= 1000000;

                remaining_time = remaining_time < 0 ? 0 : remaining_time;
                Thread.sleep((long) (remaining_time / 1000000));

                next_draw_time += draw_interval;
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

        Graphics2D g2d = (Graphics2D) g;
        
        player.draw(g2d);

        g2d.dispose();
    }
}