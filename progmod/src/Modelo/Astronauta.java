package Modelo;

public class Astronauta extends Entidade{
    private String nome;
    private String especialidade;

    public Astronauta(String nome, String especialidade){
        super(); //CHAMA O CONSTRUTOR DA CLASSE BASE
        this.nome = nome;
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Astronauta(ID: " + id + ", NOME: " + nome + ", ESPECIALIDADE: " + especialidade + ")";
    }

    //GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
