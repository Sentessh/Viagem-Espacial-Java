package visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.ItemMissao;
import Modelo.Missao;
import Persistencia.BancoDeDados;

public class JanelaGerenciarMissao extends JFrame implements ActionListener {

    private Missao missaoSelecionada; // A missão que estamos editando
    private BancoDeDados banco;
    
    private JTextField tfIdAstronauta;
    private JButton btnExcluir;
    private JTable tabelaTripulacao;
    private DefaultTableModel modelTripulacao;

    public JanelaGerenciarMissao(Missao missao, BancoDeDados banco) {
        super("Gerenciar Tripulação: " + missao.getNome());
        this.missaoSelecionada = missao;
        this.banco = banco;

        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // --- 1. TÍTULO ---
        JLabel lblTitulo = new JLabel("GERENCIADOR MISSÕES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // --- 2. PAINEL DE CONTROLE (ID e Botão EXCLUIR) ---
        // Usamos um FlowLayout para alinhar ID | INPUT | EXCLUIR na mesma linha
        JPanel pnlControle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlControle.setBorder(BorderFactory.createEtchedBorder()); // Borda leve em volta do painel
        
        // Label ID estilizado como botão na imagem (fundo preto simulado ou label simples)
        JLabel lblId = new JLabel("ID DO ASTRONAUTA:");
        lblId.setFont(new Font("Arial", Font.BOLD, 12));
        
        tfIdAstronauta = new JTextField();
        tfIdAstronauta.setPreferredSize(new Dimension(100, 30));
        
        btnExcluir = new JButton("EXCLUIR");
        btnExcluir.setPreferredSize(new Dimension(100, 30));
        btnExcluir.addActionListener(this);

        pnlControle.add(lblId);
        pnlControle.add(tfIdAstronauta);
        pnlControle.add(btnExcluir);

        gbc.gridy = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlControle, gbc);

        // --- 3. TABELA DE TRIPULAÇÃO ---
        gbc.gridy = 2; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; // Ocupa todo o resto do espaço
        gbc.fill = GridBagConstraints.BOTH;

        modelTripulacao = new DefaultTableModel();
        modelTripulacao.addColumn("ID");
        modelTripulacao.addColumn("NOME");
        modelTripulacao.addColumn("FUNÇÃO"); // Importante mostrar a função na missão

        tabelaTripulacao = new JTable(modelTripulacao);
        JScrollPane scroll = new JScrollPane(tabelaTripulacao);
        
        // Borda com título igual a imagem
        scroll.setBorder(BorderFactory.createTitledBorder("ASTRONAUTAS DA MISSÃO"));
        
        add(scroll, gbc);

        // --- EVENTOS ---
        // Clique na tabela preenche o ID
        tabelaTripulacao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaTripulacao.getSelectedRow();
                if (linha >= 0) {
                    tfIdAstronauta.setText(tabelaTripulacao.getValueAt(linha, 0).toString());
                }
            }
        });

        atualizarTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExcluir) {
            try {
                if (tfIdAstronauta.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o ID do astronauta para remover.");
                    return;
                }

                int idParaRemover = Integer.parseInt(tfIdAstronauta.getText());
                
                // Lógica: Precisamos achar o ItemMissao que tem esse astronauta
                ItemMissao itemEncontrado = null;
                
                for (ItemMissao item : missaoSelecionada.getTripulacao()) {
                    if (item.getAstronauta().getId() == idParaRemover) {
                        itemEncontrado = item;
                        break;
                    }
                }

                if (itemEncontrado != null) {
                    missaoSelecionada.removerAstronauta(itemEncontrado);
                    JOptionPane.showMessageDialog(this, "Astronauta removido da missão!");
                    atualizarTabela();
                    tfIdAstronauta.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Este astronauta não faz parte desta missão.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }

    private void atualizarTabela() {
        modelTripulacao.setRowCount(0);
        List<ItemMissao> tripulacao = missaoSelecionada.getTripulacao();
        
        for (ItemMissao item : tripulacao) {
            modelTripulacao.addRow(new Object[]{
                item.getAstronauta().getId(),
                item.getAstronauta().getNome(),
                item.getFuncao()
            });
        }
    }
}