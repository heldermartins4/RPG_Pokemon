package controllers.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.game.controls.KeyHandler;
import controllers.interfaces.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler key;

    public Player(GamePanel gp, KeyHandler key) {
        this.gp = gp;
        this.key = key;
        
        // getImagePlayer();
    }

    public void setDefaultValues(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        direction = "down";
    }

    public void getImagePlayer() {

        try {
            up1 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_left (1).png"));
            up2 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_left (2).png"));
            down1 = ImageIO.read(new File("/resources/sprites/sprites_char_walk/char_down (1).png"));
            down2 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_down (2).png"));
            left1 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_left (1).png"));
            left2 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_left (2).png"));
            right1 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_right (1).png"));
            right2 = ImageIO.read(new File("/src/main/resources/sprites/sprites_char_walk/char_right (2).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (key.up) {
            y -= speed;
        }
        if (key.down) {
            y += speed;
        }
        if (key.left) {
            x -= speed;
        }
        if (key.right) {
            x += speed;
        }
    }

    public void draw(Graphics2D g) {

        g.setColor(Color.white);
        g.fillRect(x, y, gp.tile_size, gp.tile_size);

        // BufferedImage img = null; // set default image

        // set image based on direction
        // switch (direction) {
        //     case "up":
        //         img = up1;
        //         break;
        //     case "down":
        //         img = down1;
        //         break;
        //     case "left":
        //         img = left1;
        //         break;
        //     case "right":
        //         img = right1;
        //         break;
        // }

        // g.drawImage(img, x, y, gp.tile_size, gp.tile_size, null);
        // System.out.println(getClass().getResource("/resources/sprites/sprites_char_walk/char_down (1).png").getFile());
    }
}
