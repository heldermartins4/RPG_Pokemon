package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controllers.controls.KeyHandler;
import interfaces.combat.Combat;
import interfaces.map.Map;
import interfaces.start.Start;

public class GamePanel extends JPanel implements Runnable {

//#region Screen variables

    final int scale = 3;

    public int max_screen_col = 16;
    public int max_screen_row = 12;

    private int original_tile_size = 16;
    public int tile_size = original_tile_size * scale;

    public int screen_width = tile_size * max_screen_col;
    public int screen_height = tile_size * max_screen_row;

//#endregion

//#region Classes

    private Start startPanel;
    private Map mapPanel;
    private Combat combatPanel;
    public KeyHandler key = new KeyHandler();

//#endregion

    int fps = 60;
    private long last_update_time = System.nanoTime();
    Thread game_thread;

    public enum GameState {
        START_SCREEN,
        MAP_SCREEN,
        COMBAT_SCREEN
    }

    private GameState currentState;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screen_width, screen_height));
        setBackground(Color.BLACK);

        this.setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(key);

        this.startPanel = new Start(this);
        currentState = GameState.START_SCREEN;
        startPanel.runStartSequence();
        
        this.mapPanel = new Map(this, startPanel.getPlayer(), startPanel.getRival());
        currentState = GameState.MAP_SCREEN;

        this.combatPanel = new Combat(this, startPanel.getPlayer(), startPanel.getRival());
        currentState = GameState.COMBAT_SCREEN;

        startGameThread();
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
                if (currentState == GameState.MAP_SCREEN) {
                    update();
                }

                delta--;
            }

            repaint();

            try {
                double remaining_time = (last_update_time - now + draw_interval) / 1_000_000_000;
                remaining_time = remaining_time < 0 ? 0 : remaining_time;

                Thread.sleep((long) remaining_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        if (currentState == GameState.MAP_SCREEN) {
            mapPanel.update();
        }
    }

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        if (currentState == GameState.MAP_SCREEN) {
            mapPanel.paintComponents(g);
        }
    }
}
