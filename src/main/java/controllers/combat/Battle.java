package controllers.combat;

import java.util.Scanner;

public class Battle {
    private Trainer p1;
    private Trainer p2;

    public Battle(Trainer jogador, Trainer inimigo) {
        this.p1 = jogador;
        this.p2 = inimigo;
    }

    public void batalhar() {
        Scanner teclado = new Scanner(System.in);
        boolean stop = false;
        Trainer primeiro;
        Trainer segundo;

        while (!stop) {

            // Mostrar vida dos dois
            System.out.println("\n-\n\n" + p1.getNome() + "`s " + p1.getPokemon().getNome() + ": " + p1.getPokemon().getHp() + "\n" + p2.getNome() + "`s "  + p2.getPokemon().getNome() + ": " + p2.getPokemon().getHp());
            
            // Mostrar ataques do pokemon do jogador
            System.out.println("\n\t1. " + p1.getPokemon().getMoves(0).getNome() + "\t2. " + p1.getPokemon().getMoves(1).getNome() + "\t3. " + p1.getPokemon().getMoves(2).getNome() + "\t4. " + p1.getPokemon().getMoves(3).getNome());
            
            // Checar qual ataque do player
            int i = teclado.nextInt() - 1;

            // Setar qual pokemon vai agir primeiro e ataque de cada um em ordem
            if (p1.getPokemon().getSpeed() >= p2.getPokemon().getSpeed()) {
                primeiro = p1;
                segundo = p2;
                primeiro.getPokemon().useMove(segundo.getPokemon(), i);
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    segundo.getPokemon().useMove(primeiro.getPokemon());
            } else {
                primeiro = p2;
                segundo = p1;
                primeiro.getPokemon().useMove(segundo.getPokemon());
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    segundo.getPokemon().useMove(primeiro.getPokemon(), i);
            }

            // Acaba se um morrer
            if (p1.getPokemon().getHp() == 0 || p2.getPokemon().getHp() == 0)
                stop = true;
        }

        teclado.close();
    }
}
