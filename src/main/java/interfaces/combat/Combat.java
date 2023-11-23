package interfaces.combat;

import java.awt.image.BufferedImage;

import controllers.combat.Trainer;
import interfaces.GamePanel;

public class Combat {
    
    BufferedImage gui, playerPokemon, rivalPokemon, move1, move2, move3, move4;
    Trainer player, rival;
    GamePanel screen;

    public Combat(GamePanel screen ,Trainer player, Trainer rival) {

        this.screen = screen;
        this.player = player;
        this.rival = rival;
    }
}
