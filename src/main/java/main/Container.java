package main;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import interfaces.GamePanel;

public class Container extends JFrame {
    public Container() {
        this.setTitle("2023 © IFC | Pokegame");
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
    }

    public void initialize() {
        GamePanel screen = new GamePanel();
        BufferedImage icon = null;

        try {
            icon = ImageIO.read(getClass().getResource("/assets/desktop-icon.png"));
            this.setIconImage(icon); // set a custom icon || taskbar icon 

            this.add(screen);
            this.pack();
            this.addKeyListener(screen.key);
            this.requestFocus();
            this.setLocationRelativeTo(null);
            
            screen.startGameThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
