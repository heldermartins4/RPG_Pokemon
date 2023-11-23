package controllers.interfaces.combat;

import java.awt.image.BufferedImage;

import controllers.game.*;

public class Combat {
    
    BufferedImage gui, playerPokemon, rivalPokemon, move1, move2, move3, move4;
    Trainer player, rival;

    public Combat(Trainer player, Trainer rival) {

        this.player = player;
        this.rival = rival;
    }
}
