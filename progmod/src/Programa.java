import Modelo.*;
import Persistencia.*;

public class Programa {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Viagens Espaciais ===");

        BancoDeDados bd = new BancoDeDados();

        Astronauta a1 = new Astronauta("Yuri Gagarin", "Piloto");
        Astronauta a2 = new Astronauta("Valentina Tereshkova", "Engenheira de Voo");

        Nave n1 = new Nave("Apollo 11", 3);

        Missao m1 = new Missao("Missão Apollo 11", n1);

        bd.getAstronautas().inserir(a1);
        bd.getAstronautas().inserir(a2);
        bd.getNaves().inserir(n1);
        bd.getMissoes().inserir(m1);

        ItemMissao item1 = new ItemMissao(a1, "Comandante");
        ItemMissao item2 = new ItemMissao(a2, "Engenheira de Voo");
        m1.adicionarAstronauta(item1);
        m1.adicionarAstronauta(item2);

        System.out.println("\n--- Astronautas ---");
        System.out.println(bd.getAstronautas());

        System.out.println("--- Naves ---");
        System.out.println(bd.getNaves());

        System.out.println("--- Missões ---");
        System.out.println(bd.getMissoes());

        m1.exibirTripulacao();
    }
}