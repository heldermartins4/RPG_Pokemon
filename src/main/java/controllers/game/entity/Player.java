package controllers.game.entity;

// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.game.controls.KeyHandler;
import controllers.interfaces.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler key;
    Sprites[] player_sprite;
    Sprites player_direction = new Sprites();

    int sprite_timer = 0;

    int next_x, next_y;

    private boolean is_walking = false;

    public Player(GamePanel gp, KeyHandler key) {
        this.gp = gp;
        this.key = key;

        player_sprite = new Sprites[8];  // inicializa o array
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

        // define a limiter for the next position
        this.next_x = x;
        this.next_y = y;
        player_direction.direction = "down";
    }

    public void getImagePlayer() {

        final String relative_path = "/sprites/sprites_char_walk/zeze/";

        try {
            // up1 = ImageIO.read(getClass().getResource(relative_path + "char_up_1.png"));
            // up2 = ImageIO.read(getClass().getResource(relative_path + "char_up_2.png"));
            // down1 = ImageIO.read(getClass().getResource(relative_path + "char_down_1.png"));
            // down2 = ImageIO.read(getClass().getResource(relative_path + "char_down_2.png"));
            // left1 = ImageIO.read(getClass().getResource(relative_path + "char_left_1.png"));
            // left2 = ImageIO.read(getClass().getResource(relative_path + "char_left_2.png"));
            // right1 = ImageIO.read(getClass().getResource(relative_path + "char_right_1.png"));
            // right2 = ImageIO.read(getClass().getResource(relative_path + "char_right_2.png"));
            
            for (int i = 0; i < 8; i++) {

                player_sprite[i] = new Sprites();  // inicializa cada elemento
                if (i < 2)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "zeze_u" + (i + 1) +".png"));
                else if (i < 4)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "zeze_d" + (i - 1) +".png"));
                else if (i < 6)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "zeze_l" + (i - 3) +".png"));
                else if (i < 8)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "zeze_r" + (i - 5) +".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (!is_walking) {
            if (key.up) {

                next_y = y - gp.tile_size;
                player_direction.direction = "up";
                is_walking = true;

            } else if (key.down) {

                next_y = y + gp.tile_size;
                player_direction.direction = "down";
                is_walking = true;

            } else if (key.left) {

                next_x = x - gp.tile_size;
                player_direction.direction = "left";
                is_walking = true;

            } else if (key.right) {

                next_x = x + gp.tile_size;
                player_direction.direction = "right";
                is_walking = true;

            }

            this.sprite_counting = 0;
        } else {
            if (y != next_y) {
                y += speed * Integer.signum(next_y - y);
            }
            else if (x != next_x) {
                x += speed * Integer.signum(next_x - x);
            }
            else if (x == next_x && y == next_y) {
                is_walking = false;
            }

            if (sprite_timer >= sprite) { // Ajuste o valor 10 conforme necessário para definir a velocidade da animação
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

        // g.setColor(Color.white);
        // g.fillRect(x, y, gp.tile_size, gp.tile_size);

        BufferedImage img = null; // set default image

        // // set image based on direction
        switch (player_direction.direction) {
            case "up":
                if (this.sprite_counting == 0) {
                    img = player_sprite[0].sprite_img;
                    // img = up1;
                } else if (this.sprite_counting == 1) {
                    img = player_sprite[1].sprite_img;
                    // img = up2;
                }
                break;
            case "down":
                if (this.sprite_counting == 0) {
                    img = player_sprite[2].sprite_img;
                    // img = down1;
                } else if (this.sprite_counting == 1) {
                    img = player_sprite[3].sprite_img;
                    // img = down2;
                }
                break;
            case "left":
                if (this.sprite_counting == 0) {
                    img = player_sprite[4].sprite_img;
                    // img = left1;
                } else if (this.sprite_counting == 1) {
                    img = player_sprite[5].sprite_img;
                    // img = left2;
                }
                break;
            case "right":
                if (this.sprite_counting == 0) {
                    img = player_sprite[6].sprite_img;
                    // img = right1;
                } else if (this.sprite_counting == 1) {
                    img = player_sprite[7].sprite_img;
                    // img = right2;
                }
                break;
        }

        g.drawImage(img, x, y, gp.tile_size, gp.tile_size, null);
    }
}
