package Modelo;

public class ItemMissao {
    private Astronauta astronauta;
    private String funcao;

    public ItemMissao(Astronauta astronauta, String funcao) {
        this.astronauta = astronauta;
        this.funcao = funcao;
    }

    public Astronauta getAstronauta() {
        return astronauta;
    }

    public String getFuncao() {
        return funcao;
    }

    public String toString(){
        return "Função: " + funcao + " - " + astronauta.getNome();
    }
}
