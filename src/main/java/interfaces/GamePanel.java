package interfaces;

import javax.swing.JPanel;

import controllers.controls.KeyHandler;
import controllers.entity.Player;
import interfaces.map.Map;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    
    Map map = new Map("map_1");
    Thread game_thread;
    
    public KeyHandler key = new KeyHandler();
    Player player = new Player(this, key);


    int player_x = map.tile_size * 2;
    int player_y = map.tile_size * 2;
    int player_speed = 4;

    int fps = 60;
    private long last_update_time = System.nanoTime();
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(map.screen_width, map.screen_height));

        this.setDoubleBuffered(true);
        
        player.setDefaultValues(player_x, player_y, map.tile_size, map.tile_size, player_speed);
        player.setMapToPlayer(map);
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

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

        Graphics2D g2d = (Graphics2D) g;

        map.loadMap(g2d);
        
        player.draw(g2d);

        g2d.dispose();
    }
}