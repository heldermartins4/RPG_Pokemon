package controllers.game;

public class Treinador {
    private String nome;
    private int dinheiro;
    private Pokemon pokemon;
    private final String sprite;

    public Treinador(String nome, String sprite) {
        this.nome = nome;
        this.dinheiro = 200;
        this.sprite = sprite;
    }

    public String getNome() {
        return this.nome;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public String getSprite() {
        return this.sprite;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }    
    
    public void setDinheiro(int valor) {
        this.dinheiro += valor;
    }
}
