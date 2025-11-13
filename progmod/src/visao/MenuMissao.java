package visao;

import Modelo.Astronauta;
import Modelo.ItemMissao;
import Modelo.Missao;
import Modelo.Nave;
import Persistencia.BancoDeDados;

public class MenuMissao {
    private final BancoDeDados banco;

    public MenuMissao(BancoDeDados banco) {
        this.banco = banco;
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n############ GERENCIAR MISSÕES (TRANSAÇÃO) ###########");
            System.out.println("1. Inserir Missão");
            System.out.println("2. Alterar (Adicionar/Remover Tripulante)");
            System.out.println("3. Excluir Missão");
            System.out.println("4. Visualizar por ID (com Tripulação)");
            System.out.println("5. Visualizar Todas");
            System.out.println("0. Voltar ao Menu Inicial");
            System.out.println("--------------------------------------------------");

            opcao = Input.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1: inserir(); break;
                case 2: alterarTripulacao(); break;
                case 3: excluir(); break;
                case 4: visualizarPorId(); break;
                case 5: visualizarTodos(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void inserir() {
        System.out.println("\n--- INSERIR NOVA MISSÃO ---");
        String nome = Input.lerString("Nome da Missão: ");
        int idNave = Input.lerInt("ID da Nave para a Missão: ");
        
        Nave nave = banco.getNaves().buscarPorId(idNave);
        if (nave == null) {
            System.out.println("Erro: Nave com ID " + idNave + " não encontrada. A missão não será criada.");
            return;
        }

        Missao nova = new Missao(nome, nave);
        banco.getMissoes().inserir(nova);
        System.out.println("Missão '" + nova.toString() + "' inserida com sucesso! ID: " + nova.getId());
        
        gerenciarItemMissao(nova);
    }
    
    private void alterarTripulacao() {
        System.out.println("\n--- ALTERAR TRIPULAÇÃO DE MISSÃO ---");
        int idMissao = Input.lerInt("Digite o ID da Missão para alterar a tripulação: ");
        
        Missao missao = banco.getMissoes().buscarPorId(idMissao);
        if (missao == null) {
            System.out.println("Erro: Missão com ID " + idMissao + " não encontrada.");
            return;
        }

        gerenciarItemMissao(missao);
    }

    private void gerenciarItemMissao(Missao missao) {
        int opcao;
        do {
            missao.exibirTripulacao();
            System.out.println("\n--- GERENCIAR TRIPULAÇÃO DA MISSÃO ID: " + missao.getId() + " ---");
            System.out.println("1. Adicionar Astronauta (ItemMissao)");
            System.out.println("2. Remover Astronauta (ItemMissao)");
            System.out.println("0. Finalizar Alteração e Voltar");
            System.out.println("--------------------------------------------------");

            opcao = Input.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1: adicionarAstronauta(missao); break;
                case 2: removerAstronauta(missao); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void adicionarAstronauta(Missao missao) {
        int idAstronauta = Input.lerInt("ID do Astronauta a ser adicionado: ");
        Astronauta astronauta = banco.getAstronautas().buscarPorId(idAstronauta);
        
        if (astronauta == null) {
            System.out.println("Erro: Astronauta com ID " + idAstronauta + " não encontrado.");
            return;
        }
        
        String funcao = Input.lerString("Função do Astronauta na Missão (Ex: Piloto, Comandante): ");
        
        ItemMissao item = new ItemMissao(astronauta, funcao);
        missao.adicionarAstronauta(item);
    }
    
    private void removerAstronauta(Missao missao) {
        int idAstronauta = Input.lerInt("ID do Astronauta a ser removido: ");
        Astronauta astronauta = banco.getAstronautas().buscarPorId(idAstronauta);
        
        if (astronauta == null) {
            System.out.println("Erro: Astronauta com ID " + idAstronauta + " não encontrado.");
            return;
        }
        
        String funcao = Input.lerString("Função do Astronauta que você deseja remover (Deve ser exata): ");
        
        ItemMissao itemParaRemover = new ItemMissao(astronauta, funcao);
        missao.removerAstronauta(itemParaRemover);
    }


    private void excluir() {
        System.out.println("\n--- EXCLUIR MISSÃO ---");
        int id = Input.lerInt("Digite o ID da missão a ser excluída: ");
        if (banco.getMissoes().excluir(id)) {
            System.out.println("Missão ID " + id + " excluída com sucesso.");
        } else {
            System.out.println("Erro: Missão com ID " + id + " não encontrada.");
        }
    }

    private void visualizarPorId() {
        System.out.println("\n--- VISUALIZAR MISSÃO POR ID ---");
        int id = Input.lerInt("Digite o ID da missão: ");
        Missao m = banco.getMissoes().buscarPorId(id);
        if (m != null) {
            System.out.println(m);
            m.exibirTripulacao(); 
        } else {
            System.out.println("Missão com ID " + id + " não encontrada.");
        }
    }

    private void visualizarTodos() {
        System.out.println("\n--- TODAS AS MISSÕES ---");
        String lista = banco.getMissoes().toString();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma missão cadastrada.");
        } else {
            System.out.print(lista);
        }
    }
}