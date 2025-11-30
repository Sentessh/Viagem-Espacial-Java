package agenciaespacial;

import javax.swing.SwingUtilities;
import Persistencia.BancoDeDados;
import visao.JanelaPrincipal;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BancoDeDados banco = new BancoDeDados();
            JanelaPrincipal janela = new JanelaPrincipal(banco);
            janela.setVisible(true);
        });
    }
}
