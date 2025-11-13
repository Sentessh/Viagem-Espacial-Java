package visao;

import Modelo.Astronauta;
import Persistencia.BancoDeDados;

public class MenuAstronauta {
    private final BancoDeDados banco;

    public MenuAstronauta(BancoDeDados banco) {
        this.banco = banco;
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n############ GERENCIAR ASTRONAUTAS #############");
            System.out.println("1. Inserir Astronauta");
            System.out.println("2. Alterar Astronauta");
            System.out.println("3. Excluir Astronauta");
            System.out.println("4. Visualizar por ID");
            System.out.println("5. Visualizar Todos");
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
        System.out.println("\n--- INSERIR NOVO ASTRONAUTA ---");
        String nome = Input.lerString("Nome: ");
        String especialidade = Input.lerString("Especialidade: ");
        
        Astronauta novo = new Astronauta(nome, especialidade);
        banco.getAstronautas().inserir(novo);
        System.out.println("Astronauta " + novo.getNome() + " inserido com sucesso! ID: " + novo.getId());
    }

    private void alterar() {
        System.out.println("\n--- ALTERAR ASTRONAUTA ---");
        int id = Input.lerInt("Digite o ID do astronauta a ser alterado: ");
        
        Astronauta existente = banco.getAstronautas().buscarPorId(id);
        if (existente == null) {
            System.out.println("Erro: Astronauta com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Astronauta atual: " + existente);
        
        String novoNome = Input.lerString("Novo Nome (" + existente.getNome() + "): ");
        String novaEspecialidade = Input.lerString("Nova Especialidade (" + existente.getEspecialidade() + "): ");
        
        Astronauta astronautaAtualizado = new Astronauta(novoNome, novaEspecialidade);
        
        if (banco.getAstronautas().alterar(id, astronautaAtualizado)) {
            System.out.println("Astronauta ID " + id + " alterado com sucesso.");
        } else {
            System.out.println("Erro: Não foi possível alterar o astronauta.");
        }
    }

    private void excluir() {
        System.out.println("\n--- EXCLUIR ASTRONAUTA ---");
        int id = Input.lerInt("Digite o ID do astronauta a ser excluído: ");
        if (banco.getAstronautas().excluir(id)) {
            System.out.println("Astronauta ID " + id + " excluído com sucesso.");
        } else {
            System.out.println("Erro: Astronauta com ID " + id + " não encontrado.");
        }
    }

    private void visualizarPorId() {
        System.out.println("\n--- VISUALIZAR ASTRONAUTA POR ID ---");
        int id = Input.lerInt("Digite o ID do astronauta: ");
        Astronauta a = banco.getAstronautas().buscarPorId(id);
        if (a != null) {
            System.out.println(a);
        } else {
            System.out.println("Astronauta com ID " + id + " não encontrado.");
        }
    }

    private void visualizarTodos() {
        System.out.println("\n--- TODOS OS ASTRONAUTAS ---");
        String lista = banco.getAstronautas().toString();
        if (lista.isEmpty()) {
            System.out.println("Nenhum astronauta cadastrado.");
        } else {
            System.out.print(lista);
        }
    }
}