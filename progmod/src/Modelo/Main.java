package Modelo;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Inicializando Objetos ---");
        // Cria alguns astronautas
        Astronauta a1 = new Astronauta("Yuri Gagarin", "Piloto");
        Astronauta a2 = new Astronauta("Valentina Tereshkova", "Engenheira de Voo");
        Astronauta a3 = new Astronauta("Neil Armstrong", "Comandante");
        Astronauta a4 = new Astronauta("Buzz Aldrin", "Piloto do Módulo Lunar");

        // Cria uma nave
        Nave naveApollo = new Nave("Apollo 11", 3);

        // Cria uma missão
        Missao missaoApollo11 = new Missao("Missão Apollo 11", naveApollo);

        System.out.println("\n--- Adicionando Astronautas à Missão Apollo 11 ---");
        // Cria os itens de missão (vinculando astronauta e função)
        ItemMissao item1 = new ItemMissao(a3, "Comandante da Missão");
        ItemMissao item2 = new ItemMissao(a4, "Piloto do Módulo Lunar");
        ItemMissao item3 = new ItemMissao(a2, "Engenheira de Voo");

        // Adiciona os itens à missão
        missaoApollo11.adicionarAstronauta(item1);
        missaoApollo11.adicionarAstronauta(item2);
        missaoApollo11.adicionarAstronauta(item3);

        missaoApollo11.exibirTripulacao();

        System.out.println("\n--- Tentando adicionar um 4º astronauta ---");
        // Tenta adicionar um 4º astronauta para testar a capacidade
        ItemMissao item4 = new ItemMissao(a1, "Piloto de Apoio");
        missaoApollo11.adicionarAstronauta(item4);

        System.out.println("\n--- Removendo um astronauta ---");
        missaoApollo11.removerAstronauta(item3);

        missaoApollo11.exibirTripulacao();
    }
}
