package controllers.interfaces.start;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import controllers.game.Treinador;
import controllers.interfaces.GamePanel;

public class Start extends JPanel {

    private GamePanel screen;
    private BufferedImage choose_character, character_zeze, character_heldin, choose_name, choose_rival;
    private Treinador player;
    private Treinador rival;
    
    private Runnable choicesCompletedCallback;

    public Start(GamePanel screen) {
        
        this.screen = screen;
        this.player = new Treinador(null, null);
        this.rival = new Treinador(null, null);
        getStartSprites();
        runStartSequence();
    }

    public void getStartSprites() {
        final String relative_path_background = "/sprites/start/";
        final String relative_path_characters = "/sprites/characters/";

        try {
            choose_character = ImageIO.read(getClass().getResource(relative_path_background + "choose_character.png"));
            character_zeze = ImageIO.read(getClass().getResource(relative_path_characters + "zeze/d1.png"));
            character_heldin = ImageIO.read(getClass().getResource(relative_path_characters + "heldin/d1.png"));
            choose_name = ImageIO.read(getClass().getResource(relative_path_background + "choose_name.png"));
            choose_rival = ImageIO.read(getClass().getResource(relative_path_background + "choose_rival.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runStartSequence() {
        
        namePlayer();
        nameRival();
        chooseCharacter();
    }

    public void namePlayer() {
        
        String playerName = JOptionPane.showInputDialog(screen, "Enter your name:");
        player.setNome(playerName);

        nameRival();
    }

    public void nameRival() {

        String rivalName = JOptionPane.showInputDialog(screen, "Enter your rival's name:");
        rival.setNome(rivalName);
    }

    public void chooseCharacter() {
        
    }
}
