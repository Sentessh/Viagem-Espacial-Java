package Modelo;

public class Nave extends Entidade {
    private String modelo;
    private int capacidade;

    public Nave(String modelo, int capacidade){
        super();
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Nave(ID: " + id + ", Modelo: " + modelo + ", Capacidade: " + capacidade + ")";
    }

    //GETTERS E SETTERS
    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}