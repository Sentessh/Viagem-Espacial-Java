package Modelo;

//CLASSE INTERMEDIARIA QUE CONECTA MISSAO E ASTRONAUTA
public class ItemMissao {
    private Astronauta astronauta;
    private String funcao;

    public ItemMissao(Astronauta astronauta, String funcao) {
        this.astronauta = astronauta;
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ItemMissao that = (ItemMissao) o;
        return astronauta.equals(that.astronauta) && funcao.equals(that.funcao);
    }

    @Override
    public int hashCode(){
        return java.util.Objects.hash(astronauta,funcao);
    }

    @Override
    public String toString(){
        return "Função: " + funcao + " - " + astronauta.getNome();
    }

    //GETTERS E SETTERS
    public Astronauta getAstronauta() {
        return astronauta;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setAstronauta(Astronauta astronauta) {
        this.astronauta = astronauta;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}