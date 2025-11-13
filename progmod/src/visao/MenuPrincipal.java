package visao;

import Persistencia.BancoDeDados;

public class MenuPrincipal {
    //private final BancoDeDados banco;
    private final MenuAstronauta menuAstronauta;
    private final MenuNave menuNave;
    private final MenuMissao menuMissao;

    public MenuPrincipal(BancoDeDados banco) {
        //this.banco = banco;
        this.menuAstronauta = new MenuAstronauta(banco);
        this.menuNave = new MenuNave(banco);
        this.menuMissao = new MenuMissao(banco);
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n################# MENU PRINCIPAL #################");
            System.out.println("1. Gerenciar Astronautas");
            System.out.println("2. Gerenciar Naves");
            System.out.println("3. Gerenciar Missões (Transação)");
            System.out.println("0. Sair do Programa");
            System.out.println("--------------------------------------------------");

            opcao = Input.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuAstronauta.executar();
                    break;
                case 2:
                    menuNave.executar();
                    break;
                case 3:
                    menuMissao.executar();
                    break;
                case 0:
                    break; 
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}