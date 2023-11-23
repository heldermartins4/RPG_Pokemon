package controllers.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.controls.KeyHandler;
import interfaces.GamePanel;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler key;
    private Sprites[] player_sprite;
    private int playerDirectionIndex = 0;
    private int sprite_timer = 0;
    private int next_x, next_y;
    private boolean is_walking = false;
    public String playerImagePath;

    public Player(GamePanel gp, KeyHandler key, String playerImagePath) {

        this.gp = gp;
        this.key = key;
        this.playerImagePath = playerImagePath;
        player_sprite = new Sprites[12];
        getImagePlayer();
    }

    public void setDefaultValues(int x, int y, int width, int height, int speed) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.sprite = 2;
        this.sprite_counting = 0;
        this.next_x = x;
        this.next_y = y;
        playerDirectionIndex = 3;
    }

    public void getImagePlayer() {
        final String relative_path = playerImagePath;

        try {
            for (int i = 0; i < 12; i++) {
                player_sprite[i] = new Sprites();
                String direction = getDirectionFromIndex(i / 3);
                String fileName = direction + (i % 3 + 1) + ".png";
                player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDirectionFromIndex(int index) {
        switch (index) {
            case 0:
                return "u";
            case 1:
                return "d";
            case 2:
                return "l";
            case 3:
                return "r";
            default:
                return "";
        }
    }

    public void update() {
        if (!is_walking) {
            if (key.up) {
                next_y = y - gp.tile_size;
                playerDirectionIndex = 0;
                is_walking = true;
            } else if (key.down) {
                next_y = y + gp.tile_size;
                playerDirectionIndex = 1;
                is_walking = true;
            } else if (key.left) {
                next_x = x - gp.tile_size;
                playerDirectionIndex = 2;
                is_walking = true;
            } else if (key.right) {
                next_x = x + gp.tile_size;
                playerDirectionIndex = 3;
                is_walking = true;
            }
            this.sprite_counting = 0;
        } else {
            if (y != next_y) {
                y += speed * Integer.signum(next_y - y);
            } else if (x != next_x) {
                x += speed * Integer.signum(next_x - x);
            } else if (x == next_x && y == next_y) {
                is_walking = false;
            }
    
            if (sprite_timer >= 3) {
                this.sprite_counting++;
                if (this.sprite_counting >= this.sprite) {
                    this.sprite_counting = 0;
                }
                sprite_timer = 0;
            } else {
                sprite_timer++;
            }
        }
    }

    public void draw(Graphics2D g) {
        int spriteIndex = playerDirectionIndex * 3 + this.sprite_counting;
        BufferedImage img = player_sprite[spriteIndex].sprite_img;
        g.drawImage(img, x, y, gp.tile_size, gp.tile_size, null);
    }
}
