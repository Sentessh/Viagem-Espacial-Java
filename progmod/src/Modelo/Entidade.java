package Modelo;

public abstract class Entidade {
    private static int contador = 0;
    protected int id;

    public Entidade() {
        this.id = ++contador;
    }

    public int getId() {
        return id;
    }

    @Override
    public abstract String toString();
}