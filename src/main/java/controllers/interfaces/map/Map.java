package controllers.interfaces.map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controllers.game.entity.Player;
import controllers.interfaces.GamePanel;

public class Map extends JPanel {

    int player_x = 0;
    int player_y = 0;
    int player_speed = 1;
    Player player;
    GamePanel screen;

    public Map(GamePanel screen) {
        this.screen = screen;
        this.player = new Player(screen, screen.key);
        player.setDefaultValues(player_x, player_y, screen.tile_size, screen.tile_size, player_speed);   
    }

    public void update() {
        
        player.update();
    }


    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        BufferedImage background = null;

        final String relative_path = "/sprites/map/";
        try {
            background = ImageIO.read(getClass().getResource(relative_path + "map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        g2d.drawImage(background, 0, 0, screen.screen_width, screen.screen_height, null);
        player.draw(g2d);

        g2d.dispose();
    }
}
