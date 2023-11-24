package controllers.combat;
import java.util.*;

public class Pokeleague {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        // Iniciar jogador
        System.out.println("\nWhat`s your name?");
        String nome = teclado.nextLine();
        Trainer jogador = new Trainer(nome,"");

        // Iniciar inimigo
        System.out.println("\nWhat`s your rival`s name?");
        nome = teclado.nextLine();
        Trainer inimigo = new Trainer(nome, "");

        // Iniciar pokedex
        Pokedex pokedex = new Pokedex();        
        System.out.println("\nChoose your pokemon...\n1. CHARMANDER\t2. BULBASAUR\t3. SQUIRTLE");
        int i = teclado.nextInt() - 1;
        pokedex.givePokemon(jogador, i);
        Random rn = new Random();
        i = rn.nextInt(2);
        pokedex.givePokemon(inimigo, i);
        
        // Iniciar Battle
        Battle game = new Battle(jogador, inimigo);
        game.batalhar();

        // Resultado
        if (jogador.getPokemon().getHp() == 0) 
            System.out.println("\n" + jogador.getNome() + " blacked out...");
        else
            System.out.println("\n" + jogador.getNome() + " won!");

        teclado.close();
    }

}