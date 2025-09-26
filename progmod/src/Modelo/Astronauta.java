package Modelo;

public class Astronauta extends Entidade{
    private String nome;
    private String especialidade;

    public Astronauta(String nome, String especialidade){
        super();
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String toString() {
        return "Astronauta(ID: " + idUnico + ", NOME: " + nome + ", ESPECIALIDADE: " + especialidade + ")";
    }
}
