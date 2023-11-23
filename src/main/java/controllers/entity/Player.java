package controllers.entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.controls.KeyHandler;
import interfaces.GamePanel;
import interfaces.map.Map;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler key;
    Map map;

    Sprites[] player_sprite;
    Sprites player_direction = new Sprites();

    int sprite_timer = 0;

    int next_x, next_y;

    private boolean is_walking = false;

    public Player(GamePanel gp, KeyHandler key) {

        this.gp = gp;
        this.key = key;

        player_sprite = new Sprites[12];  // inicializa o array
        getImagePlayer();
    }

    public void setDefaultValues(int x, int y, int width, int height, int speed) {
    
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.sprite = 3;
    
        this.sprite_counting = 0;
    
        // define a limiter for the next position
        this.next_x = x;
        this.next_y = y;
        player_direction.direction = "down";
    
        // Define a posição inicial aleatória
        // setRandomInitialPosition();
    }

    public void setMapToPlayer(Map map) {
        this.map = map;
    }

    public void getImagePlayer() {

        final String relative_path = "/sprites/characters/zeze/";

        try {
            for (int i = 0; i < 12; i++) {

                player_sprite[i] = new Sprites();  // initialize each element

                if (i < 3)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "u" + (i + 1) + ".png"));
                else if (i < 6)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "d" + (i - 2) + ".png"));
                else if (i < 9)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "l" + (i - 5) + ".png"));
                else if (i < 12)
                    player_sprite[i].sprite_img = ImageIO.read(getClass().getResource(relative_path + "r" + (i - 8) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRandomInitialPosition() {
        // Loop até encontrar uma posição válida
        do {
            // Gere coordenadas aleatórias
            int randomX = (int) (Math.random() * map.map_x);
            int randomY = (int) (Math.random() * map.map_y);
    
            // Converta as coordenadas do array para as coordenadas do mapa
            int mapX = randomX * map.tile_size;
            int mapY = randomY * map.tile_size;
    
            // Verifique se a posição é válida
            if (isValidPosition(mapX, mapY)) {
                // Defina a posição do jogador
                setDefaultValues(mapX, mapY, map.tile_size, map.tile_size, speed);
                return;
            }
            // Se a posição não for válida, tente novamente
        } while (true);
    }
    
    private boolean isValidPosition(int x, int y) {
        // Converte as coordenadas do mapa para as coordenadas do array
        int arrayX = x / map.tile_size;
        int arrayY = y / map.tile_size;
    
        // Verifica se as coordenadas estão dentro dos limites do mapa
        if (arrayX >= 0 && arrayX < map.map_x && arrayY >= 0 && arrayY < map.map_y) {
            // Verifica se a célula correspondente no mapa não é colisiva
            return map.map_data[arrayX][arrayY] == 0;
        }
    
        // Se as coordenadas estão fora dos limites do mapa, considera como posição inválida
        return false;
    }

    public void update() {

        if (!is_walking) {
            if (key.up) {

                next_y = y - map.tile_size;
                player_direction.direction = "up";
                is_walking = true;

            } else if (key.down) {

                next_y = y + map.tile_size;
                player_direction.direction = "down";
                is_walking = true;

            } else if (key.left) {

                next_x = x - map.tile_size;
                player_direction.direction = "left";
                is_walking = true;

            } else if (key.right) {

                next_x = x + map.tile_size;
                player_direction.direction = "right";
                is_walking = true;

            }

            this.sprite_counting = 0;
        } else {
            
            if (isValidMove(next_x, next_y)) {
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
            } else {
                is_walking = false;
                if (player_direction.direction == "up") {
                    next_y = y;
                } else if (player_direction.direction == "down") {
                    next_y = y;
                } else if (player_direction.direction == "left") {
                    next_x = x;
                } else if (player_direction.direction == "right") {
                    next_x = x;
                }
            }
        }
    }

    private boolean isValidMove(int nextX, int nextY) {
        // Converte as coordenadas do mapa para as coordenadas do array
        int arrayX = nextX / map.tile_size;
        int arrayY = nextY / map.tile_size;

        System.out.println("arrayX: " + arrayX + " arrayY: " + arrayY);
    
        // Verifica se as coordenadas estão dentro dos limites do mapa
        if (arrayX >= 0 && arrayX < map.map_x && arrayY >= 0 && arrayY < map.map_y) {
            // Verifica se a célula correspondente no mapa não é colisiva
            return map.map_data[arrayX][arrayY] == 0;
        }
    
        // Se as coordenadas estão fora dos limites do mapa, considera como movimento inválido
        return false;
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(java.awt.Color.white);
        g2d.fillRect(x, y, map.tile_size, map.tile_size);

        // BufferedImage img = null; // set default image

        // // set image based on direction
        // switch (player_direction.direction) {
        //     case "up":
        //         switch (this.sprite_counting) {
        //             case 0:
        //                 img = player_sprite[0].sprite_img;
        //                 break;
        //             case 1:
        //                 img = player_sprite[1].sprite_img;
        //                 break;
        //             case 2:
        //                 img = player_sprite[2].sprite_img;
        //                 break;
        //         }
        //         break;
        //     case "down":
        //         switch (this.sprite_counting) {
        //             case 0:
        //                 img = player_sprite[3].sprite_img;
        //                 break;
        //             case 1:
        //                 img = player_sprite[4].sprite_img;
        //                 break;
        //             case 2:
        //                 img = player_sprite[5].sprite_img;
        //                 break;
        //         }
        //         break;
        //     case "left":
        //         switch (this.sprite_counting) {
        //             case 0:
        //                 img = player_sprite[6].sprite_img;
        //                 break;
        //             case 1:
        //                 img = player_sprite[7].sprite_img;
        //                 break;
        //             case 2:
        //                 img = player_sprite[8].sprite_img;
        //                 break;
        //         }
        //         break;
        //     case "right":
        //         switch (this.sprite_counting) {
        //             case 0:
        //                 img = player_sprite[9].sprite_img;
        //                 break;
        //             case 1:
        //                 img = player_sprite[10].sprite_img;
        //                 break;
        //             case 2:
        //                 img = player_sprite[11].sprite_img;
        //                 break;
        //         }
        //         break;
        // }

        // g.drawImage(img, x, y, map.tile_size, map.tile_size, null);
    }
}