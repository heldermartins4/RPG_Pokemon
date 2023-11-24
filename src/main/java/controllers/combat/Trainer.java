package controllers.combat;

public class Trainer {
    
    private String nome;
    private int dinheiro;
    private Pokemon pokemon;
    private String character;

    public Trainer(String nome, String character) {
        this.nome = nome;
        this.dinheiro = 200;
        this.character = character;
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

    public String getCharater() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }    
    
    public void setDinheiro(int valor) {
        this.dinheiro += valor;
    }
}
