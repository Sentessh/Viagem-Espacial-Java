package Persistencia;

public class IdNaoEncontrado extends Exception{
    public IdNaoEncontrado(int id){
        super("Entidade com o id: "+id+" não encontrado");
    }
}