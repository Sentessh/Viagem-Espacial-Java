package visao;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.Nave;
import Persistencia.BancoDeDados;

public class JanelaNaves extends JFrame implements ActionListener {

    private JTextField tfId, tfModelo, tfCapacidade;
    private JButton btsv, btal, btrm;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private BancoDeDados banco;

    public JanelaNaves(BancoDeDados banco) {
        super("Gerenciar Naves");
        this.banco = banco;

        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); 

        JLabel lblTituloTop = new JLabel("NAVES");
        lblTituloTop.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 20, 10);
        add(lblTituloTop, gbc);

        tfId = new JTextField();
        tfId.setVisible(false);
        add(tfId);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridy = 1;
        gbc.gridx = 0; gbc.weightx = 0.0;
        JLabel lblNome = new JLabel("MODELO");
        lblNome.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblNome, gbc);

        tfModelo = new JTextField();
        gbc.gridx = 1; gbc.weightx = 1.0;
        add(tfModelo, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0; gbc.weightx = 0.0;
        JLabel lblEsp = new JLabel("CAPACIDADE");
        lblEsp.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblEsp, gbc);

        tfCapacidade = new JTextField();
        gbc.gridx = 1; gbc.weightx = 1.0;
        add(tfCapacidade, gbc);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btsv = new JButton("ADICIONAR");
        btsv.addActionListener(this);
        btal = new JButton("ALTERAR");
        btal.addActionListener(this);
        btrm = new JButton("EXCLUIR");
        btrm.addActionListener(this);

        panelBotoes.add(btsv);
        panelBotoes.add(btal);
        panelBotoes.add(btrm);

        gbc.gridy = 3; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 10, 20, 10);
        add(panelBotoes, gbc);

        JLabel lblTituloTabela = new JLabel("NAVES CADASTRADAS");
        lblTituloTabela.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(lblTituloTabela, gbc);

        modeloTabela = new DefaultTableModel() {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("MODELO");
        modeloTabela.addColumn("CAPACIDADE");

        tabela = new JTable(modeloTabela);
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    tfId.setText(tabela.getValueAt(linha, 0).toString());
                    tfModelo.setText(tabela.getValueAt(linha, 1).toString());
                    tfCapacidade.setText(tabela.getValueAt(linha, 2).toString());
                }
            }
        });

        var scroll = new JScrollPane(tabela);
        gbc.gridy = 5; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(scroll, gbc);

        atualizarTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (e.getSource() == btsv) {
                if(tfModelo.getText().isEmpty() || tfCapacidade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos!"); return;
                }

                int capacidade = Integer.parseInt(tfCapacidade.getText());
                Nave nova = new Nave(tfModelo.getText(), capacidade);

                if (banco.getNaves().inserir(nova)) {
                    JOptionPane.showMessageDialog(this, "Nave adicionada com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: ID já existe!");
                }

            } else if (e.getSource() == btal) {
                if (tfId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Selecione uma nave na tabela para alterar.");
                    return;
                }

                int id = Integer.parseInt(tfId.getText());
                int capacidade = Integer.parseInt(tfCapacidade.getText());

                Nave naveEditada = new Nave(tfModelo.getText(), capacidade);
                
                if (banco.getNaves().alterar(id, naveEditada)) {
                    JOptionPane.showMessageDialog(this, "Nave alterada com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Nave não encontrada para alteração.");
                }

            } else if (e.getSource() == btrm) {
                if (tfId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Selecione uma nave na tabela para excluir.");
                    return;
                }
                
                int id = Integer.parseInt(tfId.getText());
                int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                
                if (confirmacao == JOptionPane.YES_OPTION) {
                    if (banco.getNaves().excluir(id)) {
                        JOptionPane.showMessageDialog(this, "Nave excluída com sucesso.");
                        limparCampos();
                        atualizarTabela();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: Nave não encontrada para exclusão.");
                    }
                }
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "A Capacidade deve ser um número inteiro!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        List<Nave> lista = banco.getNaves().getTodos();
        for (Nave n : lista) {
            modeloTabela.addRow(new Object[] { n.getId(), n.getModelo(), n.getCapacidade() });
        }
    }

    private void limparCampos() {
        tfId.setText("");
        tfModelo.setText("");
        tfCapacidade.setText("");
        tabela.clearSelection();
    }
}