package visao;

import java.awt.BorderLayout;
import java.awt.Color;
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

import Modelo.Astronauta;
import Modelo.ItemMissao;
import Modelo.Missao;
import Modelo.Nave;
import Persistencia.BancoDeDados;

public class JanelaMissoes extends JFrame implements ActionListener {

    private JTextField tfNome, tfIdNave, tfIdAstroInput, tfIdMissaoGerenciar;
    private JButton btnCriar, btnAddAstro, btnExcluir, btnGerenciar;
    private JTable tabelaAstronautas, tabelaMissoes;
    private DefaultTableModel modelAstro, modelMissao;
    private BancoDeDados banco;

    public JanelaMissoes(BancoDeDados banco) {
        super("Gerenciar Missões");
        this.banco = banco;

        setSize(900, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); 

        // --- 1. TÍTULO ---
        JLabel lblTitulo = new JLabel("MISSÕES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0); 
        add(lblTitulo, gbc);

        // --- 2. NOME ---
        gbc.gridy = 1; gbc.gridx = 0; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("NOME:"), gbc);

        tfNome = new JTextField();
        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfNome, gbc);

        // --- 3. ID NAVE ---
        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("ID DA NAVE:"), gbc);

        tfIdNave = new JTextField();
        tfIdNave.setPreferredSize(new Dimension(100, 25));
        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(tfIdNave, gbc);

        // --- 4. BOTÕES SUPERIORES ---
        JPanel pnlBotoesCima = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        
        btnCriar = new JButton("CRIAR");
        btnCriar.setPreferredSize(new Dimension(100, 40)); 
        
        btnAddAstro = new JButton("ADICIONAR ASTRONAUTA");
        btnAddAstro.setPreferredSize(new Dimension(200, 40));

        tfIdAstroInput = new JTextField();
        tfIdAstroInput.setPreferredSize(new Dimension(150, 45)); 
        tfIdAstroInput.setBorder(BorderFactory.createTitledBorder("DIGITE ID ASTRONAUTA"));
        
        btnExcluir = new JButton("EXCLUIR");
        btnExcluir.setPreferredSize(new Dimension(100, 40));
        btnExcluir.setBackground(new Color(255, 255, 255));

        pnlBotoesCima.add(btnCriar);
        pnlBotoesCima.add(btnAddAstro);
        pnlBotoesCima.add(tfIdAstroInput);
        pnlBotoesCima.add(new JLabel("   ")); 
        pnlBotoesCima.add(btnExcluir);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 10, 5);
        add(pnlBotoesCima, gbc);

        // --- 5. BOTÕES INFERIORES ---
        JPanel pnlBotoesBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        
        btnGerenciar = new JButton("GERENCIAR");
        btnGerenciar.setPreferredSize(new Dimension(120, 40));
        
        tfIdMissaoGerenciar = new JTextField();
        tfIdMissaoGerenciar.setPreferredSize(new Dimension(150, 45));
        tfIdMissaoGerenciar.setBorder(BorderFactory.createTitledBorder("DIGITE ID MISSÃO"));
        
        pnlBotoesBaixo.add(btnGerenciar);
        pnlBotoesBaixo.add(tfIdMissaoGerenciar);

        gbc.gridy = 4;
        add(pnlBotoesBaixo, gbc);

        // --- 6. TABELA ASTRONAUTAS ---
        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 3;
        gbc.weighty = 0.4; 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 5, 10);

        modelAstro = new DefaultTableModel();
        modelAstro.addColumn("ID"); modelAstro.addColumn("NOME"); modelAstro.addColumn("ESPECIALIDADE");
        tabelaAstronautas = new JTable(modelAstro);
        JScrollPane scrollAstro = new JScrollPane(tabelaAstronautas);
        scrollAstro.setBorder(BorderFactory.createTitledBorder("ASTRONAUTAS DISPONÍVEIS"));
        
        add(scrollAstro, gbc);

        // --- 7. TABELA MISSÕES ---
        gbc.gridy = 6; gbc.weighty = 0.6; 
        
        modelMissao = new DefaultTableModel();
        modelMissao.addColumn("ID"); modelMissao.addColumn("NOME"); modelMissao.addColumn("NAVE");
        tabelaMissoes = new JTable(modelMissao);
        JScrollPane scrollMissao = new JScrollPane(tabelaMissoes);
        scrollMissao.setBorder(BorderFactory.createTitledBorder("MISSÕES"));

        add(scrollMissao, gbc);

        configurarAcoes();
        atualizarTabelas();
    }

    private void configurarAcoes() {
        btnCriar.addActionListener(this);
        btnAddAstro.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnGerenciar.addActionListener(this);

        tabelaAstronautas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaAstronautas.getSelectedRow();
                if(linha >= 0) tfIdAstroInput.setText(tabelaAstronautas.getValueAt(linha, 0).toString());
            }
        });

        tabelaMissoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaMissoes.getSelectedRow();
                if(linha >= 0) tfIdMissaoGerenciar.setText(tabelaMissoes.getValueAt(linha, 0).toString());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // --- BOTÃO CRIAR MISSÃO ---
            if (e.getSource() == btnCriar) {
                if (tfNome.getText().isEmpty() || tfIdNave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha Nome e ID da Nave."); return;
                }
                int idNave = Integer.parseInt(tfIdNave.getText());
                
                // Validação: Nave já está em missão?
                if (naveJaTemMissao(idNave)) {
                    JOptionPane.showMessageDialog(this, "ERRO: Essa Nave já está alocada em outra missão!");
                    return;
                }

                Nave nave = banco.getNaves().buscarPorId(idNave);
                if (nave == null) { JOptionPane.showMessageDialog(this, "Nave não encontrada!"); return; }

                Missao novaMissao = new Missao(tfNome.getText(), nave);
                if (banco.getMissoes().inserir(novaMissao)) {
                    JOptionPane.showMessageDialog(this, "Missão Criada!");
                    tfNome.setText(""); tfIdNave.setText(""); atualizarTabelas();
                } else { JOptionPane.showMessageDialog(this, "Erro ao criar."); }


            // --- BOTÃO ADICIONAR ASTRONAUTA ---
            } else if (e.getSource() == btnAddAstro) {
                if (tfIdMissaoGerenciar.getText().isEmpty() || tfIdAstroInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha ID Missão e ID Astronauta."); return;
                }
                int idMissao = Integer.parseInt(tfIdMissaoGerenciar.getText());
                int idAstro = Integer.parseInt(tfIdAstroInput.getText());
                
                // Validação: Astronauta já está em missão?
                if (astronautaJaTemMissao(idAstro)) {
                    JOptionPane.showMessageDialog(this, "ERRO: Esse Astronauta já está em outra missão!");
                    return;
                }

                Missao missao = banco.getMissoes().buscarPorId(idMissao);
                Astronauta astro = banco.getAstronautas().buscarPorId(idAstro);
                
                if (missao != null && astro != null) {
                    // --- CORREÇÃO: Não pergunta função, usa a Especialidade do Astronauta automaticamente ---
                    missao.adicionarAstronauta(new ItemMissao(astro, astro.getEspecialidade()));
                    JOptionPane.showMessageDialog(this, "Adicionado com sucesso!");
                    
                } else { JOptionPane.showMessageDialog(this, "Dados não encontrados."); }

            } else if (e.getSource() == btnExcluir) {
                if (tfIdMissaoGerenciar.getText().isEmpty()) {
                     JOptionPane.showMessageDialog(this, "Selecione uma missão."); return;
                }
                if (banco.getMissoes().excluir(Integer.parseInt(tfIdMissaoGerenciar.getText()))) {
                    JOptionPane.showMessageDialog(this, "Excluída.");
                    tfIdMissaoGerenciar.setText(""); atualizarTabelas();
                } else { JOptionPane.showMessageDialog(this, "Erro ao excluir."); }

            } else if (e.getSource() == btnGerenciar) {
                if (tfIdMissaoGerenciar.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Digite ID."); return; }
                int idMissao = Integer.parseInt(tfIdMissaoGerenciar.getText());
                Missao missao = banco.getMissoes().buscarPorId(idMissao);
                if (missao != null) {
                    new JanelaGerenciarMissao(missao, banco).setVisible(true);
                } else { JOptionPane.showMessageDialog(this, "Missão não encontrada."); }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite apenas números nos IDs.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizarTabelas() {
        modelAstro.setRowCount(0);
        for (Astronauta a : banco.getAstronautas().getTodos()) 
            modelAstro.addRow(new Object[]{a.getId(), a.getNome(), a.getEspecialidade()});

        modelMissao.setRowCount(0);
        for (Missao m : banco.getMissoes().getTodos()) 
            modelMissao.addRow(new Object[]{m.getId(), m.getNome(), m.getNave().getModelo()});
    }

    // --- MÉTODOS DE VALIDAÇÃO ---

    private boolean naveJaTemMissao(int idNave) {
        List<Missao> todasMissoes = banco.getMissoes().getTodos();
        for (Missao m : todasMissoes) {
            if (m.getNave().getId() == idNave) {
                return true; 
            }
        }
        return false; 
    }

    private boolean astronautaJaTemMissao(int idAstro) {
        List<Missao> todasMissoes = banco.getMissoes().getTodos();
        for (Missao m : todasMissoes) {
            for (ItemMissao item : m.getTripulacao()) {
                if (item.getAstronauta().getId() == idAstro) {
                    return true; 
                }
            }
        }
        return false; 
    }
}