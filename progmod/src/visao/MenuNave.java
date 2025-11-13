package visao;

import Modelo.Nave;
import Persistencia.BancoDeDados;

public class MenuNave {
    private final BancoDeDados banco;

    public MenuNave(BancoDeDados banco) {
        this.banco = banco;
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n############### GERENCIAR NAVES ###############");
            System.out.println("1. Inserir Nave");
            System.out.println("2. Alterar Nave");
            System.out.println("3. Excluir Nave");
            System.out.println("4. Visualizar por ID");
            System.out.println("5. Visualizar Todas");
            System.out.println("0. Voltar ao Menu Inicial");
            System.out.println("--------------------------------------------------");

            opcao = Input.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1: inserir(); break;
                case 2: alterar(); break;
                case 3: excluir(); break;
                case 4: visualizarPorId(); break;
                case 5: visualizarTodos(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void inserir() {
        System.out.println("\n--- INSERIR NOVA NAVE ---");
        String modelo = Input.lerString("Modelo: ");
        int capacidade = Input.lerInt("Capacidade máxima de tripulantes: ");
        
        Nave nova = new Nave(modelo, capacidade);
        banco.getNaves().inserir(nova);
        System.out.println("Nave " + nova.getModelo() + " inserida com sucesso! ID: " + nova.getId());
    }

    private void alterar() {
        System.out.println("\n--- ALTERAR NAVE ---");
        int id = Input.lerInt("Digite o ID da nave a ser alterada: ");
        
        Nave existente = banco.getNaves().buscarPorId(id);
        if (existente == null) {
            System.out.println("Erro: Nave com ID " + id + " não encontrada.");
            return;
        }

        System.out.println("Nave atual: " + existente);
        
        String novoModelo = Input.lerString("Novo Modelo (" + existente.getModelo() + "): ");
        int novaCapacidade = Input.lerInt("Nova Capacidade (" + existente.getCapacidade() + "): ");
        
        Nave naveAtualizada = new Nave(novoModelo, novaCapacidade);
        
        if (banco.getNaves().alterar(id, naveAtualizada)) {
            System.out.println("Nave ID " + id + " alterada com sucesso.");
        } else {
            System.out.println("Erro: Não foi possível alterar a nave.");
        }
    }

    private void excluir() {
        System.out.println("\n--- EXCLUIR NAVE ---");
        int id = Input.lerInt("Digite o ID da nave a ser excluída: ");
        if (banco.getNaves().excluir(id)) {
            System.out.println("Nave ID " + id + " excluída com sucesso.");
        } else {
            System.out.println("Erro: Nave com ID " + id + " não encontrada.");
        }
    }

    private void visualizarPorId() {
        System.out.println("\n--- VISUALIZAR NAVE POR ID ---");
        int id = Input.lerInt("Digite o ID da nave: ");
        Nave n = banco.getNaves().buscarPorId(id);
        if (n != null) {
            System.out.println(n);
        } else {
            System.out.println("Nave com ID " + id + " não encontrada.");
        }
    }

    private void visualizarTodos() {
        System.out.println("\n--- TODAS AS NAVES ---");
        String lista = banco.getNaves().toString();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma nave cadastrada.");
        } else {
            System.out.print(lista);
        }
    }
}