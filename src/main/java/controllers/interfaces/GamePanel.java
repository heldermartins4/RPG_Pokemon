package controllers.interfaces;

import javax.swing.JPanel;

import controllers.game.controls.KeyHandler;
import controllers.interfaces.map.Map;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {
    
    final int scale = 3;

    public int max_screen_col = 16;
    public int max_screen_row = 12;

    private int original_tile_size = 16;
    public int tile_size = original_tile_size * scale;  

    public int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    int fps = 60;
    private long last_update_time = System.nanoTime();

    public KeyHandler key = new KeyHandler();

    // private Start startPanel;
    private Map mapPanel;

    Thread game_thread;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // this.startPanel = new Start(this);
        // cardPanel.add(startPanel, "start");

        this.mapPanel = new Map(this);
        cardPanel.add(mapPanel, "map");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "start");

        this.setDoubleBuffered(true);
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

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public void update() {
        
        mapPanel.update();
    }

    public void paintComponent(Graphics g) {
        
        mapPanel.paintComponent(g);
    }
}