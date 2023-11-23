package interfaces.combat;

import java.awt.image.BufferedImage;

import controllers.combat.Trainer;

public class Combat {
    
    BufferedImage gui, playerPokemon, rivalPokemon, move1, move2, move3, move4;
    Trainer player, rival;

    public Combat(Trainer player, Trainer rival) {

        this.player = player;
        this.rival = rival;
    }
}
