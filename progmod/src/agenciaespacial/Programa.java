package agenciaespacial;

import visao.MenuPrincipal;
import Persistencia.BancoDeDados;

public class Programa {

    public static void main(String[] args) {  
        BancoDeDados banco = new BancoDeDados();
        MenuPrincipal menu = new MenuPrincipal(banco);
        System.out.println("### SISTEMA DE GERENCIAMENTO DE MISSÕES ESPACIAIS ###");
        menu.executar();
        System.out.println("\nPrograma encerrado. Tenha um bom dia!");
    }
}