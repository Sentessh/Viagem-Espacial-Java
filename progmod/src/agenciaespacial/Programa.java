package agenciaespacial;

import Persistencia.BancoDeDados;
import visao.JanelaPrincipal;

public class Programa {

    public static void main(String[] args) {
        BancoDeDados banco = new BancoDeDados();
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.setVisible(true);
    }
}
