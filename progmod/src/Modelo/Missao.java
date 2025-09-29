package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Missao extends Entidade {
    private String nome;
    private Nave nave;
    private List<ItemMissao> tripulacao;

    public Missao(String nome, Nave nave) {
        super();
        this.nome = nome;
        this.nave = nave;
        this.tripulacao = new ArrayList<>();
    }

    //GERENCIAMENTO DA TRIPULACAO
    //ADICIONA ASTRONAUTA
    public void adicionarAstronauta(ItemMissao item){
        if(tripulacao.size() < nave.getCapacidade()){
            tripulacao.add(item);
            System.out.println("Astronauta " + item.getAstronauta().getNome() + " adicionado com sucesso!");
        } else {
            System.out.println("A capacidade máxima da nave foi atingida. Não é possível adicionar novos astronautas.");
        }
    }

    //REMOVE ASTRONAUTA
    public void removerAstronauta(ItemMissao item){
        if(tripulacao.remove(item)){
            System.out.println("Astronauta " + item.getAstronauta().getNome() + " removido com sucesso!");
        } else {
            System.out.println("Esse astronauta não está na tripulação presente na missão.");
        }
    }

    //EXIBE TRIPULACAO COMPLETA
    public void exibirTripulacao(){
        System.out.println("\n#--- TRIPULAÇÃO DA MISSÃO " + this.nome + "(" + tripulacao.size() + "|" + nave.getCapacidade() + ") ---#");
        for(ItemMissao item : tripulacao){
            System.out.println("- " + item);
        }
    }

    @Override
    public String toString(){
        return "Missao(ID: " + id + ", Nome: " + nome + ", Nave: " + nave.getModelo() + ")";
    }
}
