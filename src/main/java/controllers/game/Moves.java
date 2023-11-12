package controllers.game;

public class Moves {
    private String nome;
    private String tipo;
    private int power;
    private String category;

    public Moves(String nome, String tipo, int power, String category) {
        this.nome = nome;
        this.tipo = tipo;
        this.power = power;
        this.category = category;
    }

    public void setPower(int i) {
        this.power += i;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPower() {
        return power;
    }

    public String getCategory() {
        return category;
    }
}
