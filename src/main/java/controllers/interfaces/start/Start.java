package controllers.interfaces.start;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controllers.game.Treinador;

public class Start {

    // Etapas da criacao do personagem
    BufferedImage choose_character, choose_name, choose_rival;
    public Treinador player;
    public Treinador rival;


    public Start() {

        this.player = new Treinador(null, null);
        this.rival = new Treinador(null, null);

        getStartSprites();
        chooseCharacter(player, rival);
        namePlayer(player);
        nameRival(rival);
    }

    public void getStartSprites() {

        final String relative_path = "/sprites/start/";

        try {
            
            choose_character = ImageIO.read(getClass().getResource(relative_path + "choose_character.png"));
            choose_name = ImageIO.read(getClass().getResource(relative_path + "choose_name.png"));
            choose_rival = ImageIO.read(getClass().getResource(relative_path + "choose_rival.png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        int step = 0;
        switch (step) {

            // Choose character
            case 0:
                /* Desenhar o fundo e as escolhar e rodar o chooseCharacter */
                step += 1;                    
                break;

            // Choose name
            case 1:
                /* Desenhar o fundo e a leitura da string e rodar o chooseName */
                step += 1;                    
                break;

            // Choose rival
            case 2:
                /* Desenhar o fundo e a leitura da string e rodar o chooseRival */
                break;
        }
    }

    public void chooseCharacter(Treinador player, Treinador rival) {
        String choice = null;

        /* 
         *  Desenhar as sprites dos possiveis personagens e alterar elas conforme input pra direita ou esquerda
         *  dando highlight para a escolhida e checar o input enter para definir o "choice" conforme a sprite
         *  em highlight no momento
         */
        
        String characterPlayer = choice;
        this.player.setCharacter(characterPlayer);

        String characterRival = (characterPlayer == "sprites/sprites_char_walk/zeze") ? "sprites/sprites_char_walk/heldin" : "sprites/sprites_char_walk/zeze";
        this.rival.setCharacter(characterRival);
    }

    public void namePlayer(Treinador player) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("\nWhat`s your name?");
        String nome = teclado.nextLine();
        this.player.setNome(nome);

        teclado.close();
    }

    public void nameRival(Treinador rival) {

        Scanner teclado = new Scanner(System.in);
        
        System.out.println("\nWhat`s your rival`s name?");
        String nome = teclado.nextLine();
        this.rival.setNome(nome);

        teclado.close();
    }
}