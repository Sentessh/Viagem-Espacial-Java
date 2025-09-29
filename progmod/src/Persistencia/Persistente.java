package Persistencia;

import Modelo.Entidade;
import java.util.ArrayList;
import java.util.List;

public class Persistente<T extends Entidade> {
    private List<T> lista;

    public Persistente() {
        this.lista = new ArrayList<>();
    }

    public void inserir(T obj) {
        lista.add(obj);
    }
    public boolean alterar(int id, T novoObj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                lista.set(i, novoObj);
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        return lista.removeIf(obj -> obj.getId() == id);
    }

    public T buscarPorId(int id) {
        for (T obj : lista) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
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