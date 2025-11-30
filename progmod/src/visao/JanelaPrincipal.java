package visao;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Persistencia.BancoDeDados;

public class JanelaPrincipal extends JFrame implements ActionListener {

    private JButton btnAstronautas, btnNaves, btnMissoes, btnSair;
    private BancoDeDados banco; 
    public JanelaPrincipal(BancoDeDados banco) {
        super("Agência Espacial - Menu Principal");
        this.banco = banco;

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("SISTEMA DE GESTÃO ESPACIAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(titulo, gbc);

        btnAstronautas = criarBotao("Gerenciar Astronautas");
        gbc.gridy = 1;
        add(btnAstronautas, gbc);

        btnNaves = criarBotao("Gerenciar Naves");
        gbc.gridy = 2;
        add(btnNaves, gbc);

        btnMissoes = criarBotao("Gerenciar Missões");
        gbc.gridy = 3;
        add(btnMissoes, gbc);

        btnSair = new JButton("Sair");
        btnSair.addActionListener(this);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSair, gbc);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAstronautas) {

            new JanelaAstronauta(banco).setVisible(true);
            
        } else if (e.getSource() == btnNaves) {

            new JanelaNaves(banco).setVisible(true);

        } else if (e.getSource() == btnMissoes) {

            new JanelaMissoes(banco).setVisible(true);

        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }
}