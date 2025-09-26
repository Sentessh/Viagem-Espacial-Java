package Modelo;

public class Nave extends Entidade {
    private String modelo;
    private int capacidade;

    public Nave(String modelo, int capacidade){
        super();
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String toString() {
        return "Nave(ID: " + idUnico + ", Modelo: " + modelo + ", Capacidade: " + capacidade + ")";
    }
}
