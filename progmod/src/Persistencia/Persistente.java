package Persistencia;

import Modelo.Entidade;
import java.util.ArrayList;
import java.util.List;

public class Persistente<T extends Entidade> {
    private List<T> lista;

    public Persistente() {
        this.lista = new ArrayList<>();
    }

    public boolean inserir(T obj) {
        lista.add(obj);
        return true;
    }
    public boolean alterar(int id, T novoObj) {
        try{
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == id) {
                    lista.set(i, novoObj);
                    return true;
                }
            }
            throw new IdNaoEncontrado(id);
        }catch(IdNaoEncontrado e){
            System.err.println("Erro: "+e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try{    
            boolean remove = lista.removeIf(obj -> obj.getId() == id);
            if(!remove){
                throw new IdNaoEncontrado(id);
            }
            return true;
        }
        catch(IdNaoEncontrado e){
            System.err.println("Erro: "+e.getMessage());
            return false;
        }
    }

    public T buscarPorId(int id){ //TEM QUE BOTAR IF OBJ == NULL NA INTERFACE
        try{
            for (T obj : lista) {
                if (obj.getId() == id) {
                    return obj;
                }
            }
            throw new IdNaoEncontrado(id);

        }catch(IdNaoEncontrado e){
            System.err.println("Erro: "+e.getMessage());
            return null;
        }
    }

    public List<T> getTodos() {
        return lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T obj : lista) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }
}
