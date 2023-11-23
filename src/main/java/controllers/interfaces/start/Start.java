package controllers.interfaces.start;
// teste 
import javax.swing.*;

import controllers.game.Trainer;
import controllers.interfaces.GamePanel;

public class Start extends JPanel {

    private GamePanel screen;
    private Trainer player;
    private Trainer rival;
    private ChooseSkin ChooseSkin;

    public Start(GamePanel screen) {
        this.screen = screen;
        this.player = new Trainer(null, null);
        this.rival = new Trainer(null, null);
        this.ChooseSkin = new ChooseSkin();
        add(ChooseSkin);
    }

    public void runStartSequence() {

        namePlayer();
        nameRival();
        chooseCharacter();
    }

    public void namePlayer() {
        
        String playerName = JOptionPane.showInputDialog(screen, "Enter your name:");
        player.setNome(playerName);
    }

    public void nameRival() {

        String rivalName = JOptionPane.showInputDialog(screen, "Enter your rival's name:");
        rival.setNome(rivalName);
    }

    public void chooseCharacter() {

        JOptionPane.showMessageDialog(screen, ChooseSkin, "Who are you?", JOptionPane.PLAIN_MESSAGE);

        String selectedSkin = ChooseSkin.getSelectedSkin();

        player.setCharacter(selectedSkin);
        rival.setCharacter(selectedSkin.equals("/sprites/characters/zeze/") ? "/sprites/characters/heldin/" : "/sprites/characters/zeze/");
    }

    public Trainer getPlayer() {
        return player;
    }

    public Trainer getRival() {
        return rival;
    }
}