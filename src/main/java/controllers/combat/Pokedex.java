package controllers.combat;

public class Pokedex {
    // Sets únicos pra cada pokemon
    Moves charmanderMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS"),
        new Moves("SCRATCH", "NORMAL", 40, "PHYSICAL"),
        new Moves("EMBER", "FIRE", 40, "SPECIAL"),
        new Moves("SMOKESCREEN", "NORMAL", 0, "STATUS")
    };

    Moves bulbasaurMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS"),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("VINE WHIP", "GRASS", 45, "PHYSICAL"),
        new Moves("GROWTH", "NORMAL", 0, "STATUS")
    };

    Moves squirtleMoves[] = {
        new Moves("TAIL WHIP", "NORMAL", 0, "STATUS"),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("WATER GUN", "WATER", 40, "SPECIAL"),
        new Moves("WITHDRAW", "WATER", 0, "STATUS")
    };

    String charmanderSprite = "";
    String squirtleSprite = "";
    String bulbasaurSprite = "";

    // Da uma instância NOVA de pokemon para um objeto treinador
    public void givePokemon(Trainer treinador, int i) {
        switch (i) {
            case 0:
                treinador.setPokemon(new Pokemon("CHARMANDER", "FIRE", 39, 52, 60, 43, 50, 65, 100, charmanderMoves, charmanderSprite));   
                break;

            case 1:
                treinador.setPokemon(new Pokemon("BULBASAUR", "GRASS", 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves, squirtleSprite));   
                break;

            case 2:
                treinador.setPokemon(new Pokemon("SQUIRTLE", "WATER", 44, 48, 50, 65, 64, 43, 100, squirtleMoves, bulbasaurSprite));   
                break;
                
            default:
                break;
        }
    }

}
