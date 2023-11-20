package controllers.interfaces.start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import controllers.game.Treinador;
import controllers.interfaces.GamePanel;

public class Start extends JPanel {

    private GamePanel gamePanel;
    private BufferedImage choose_character, choose_name, choose_rival;
    private Treinador player;
    private Treinador rival;

    public Start(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.player = new Treinador(null, null);
        this.rival = new Treinador(null, null);
        getStartSprites();
        runStartSequence();
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

    public void runStartSequence() {

        chooseCharacter(player);
        namePlayer(player);
        nameRival();

        // Trocar para o painel do mapa
        gamePanel.showPanel("map");
    }

    public void chooseCharacter(Treinador player) {

        String choice = null;
        System.out.println("chooseCharacter()");

        /* 
         * Lógica para escolher o personagem do jogador.
         * Desenhar as sprites dos possíveis personagens, 
         * alterar conforme input para direita ou esquerda,
         * dar highlight para a escolhida e checar o input enter
         * para definir o "choice" conforme a sprite em highlight.
         */

        String characterPlayer = choice;
        this.player.setCharacter(characterPlayer);

        String characterRival = (characterPlayer.equals("sprites/sprites_char_walk/zeze")) 
            ? "sprites/sprites_char_walk/heldin" 
            : "sprites/sprites_char_walk/zeze";
        this.rival.setCharacter(characterRival);
    }

    public void namePlayer(Treinador player) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("namePlayer()");
        String nome = teclado.nextLine();
        this.player.setNome(nome);
        teclado.close();
    }

    public void nameRival() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("nameRival()");
        String nome = teclado.nextLine();
        this.rival.setNome(nome);
        teclado.close();
    }
}