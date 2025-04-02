import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {
    public GUI() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // Parte principal
        setTitle("Sistema da Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER));


        JPanel painelTopo = new JPanel(new GridBagLayout());
        painelTopo.setPreferredSize(new Dimension(550, 200));
        painelTopo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Bem-Vindo à biblioteca.",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Times New Roman", Font.BOLD, 40)
        ));

        // Definindo as restrições do GridBagConstraints
        GridBagConstraints gbcinicio = new GridBagConstraints();
        gbcinicio.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes
        gbcinicio.anchor = GridBagConstraints.CENTER;   // Centralizar os botões
        gbcinicio.gridwidth = GridBagConstraints.REMAINDER; // Ocupa toda a largura disponível

        // Botão "Sair"
        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSair.setBackground(new Color(0, 51, 25)); // Verde escuro
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setPreferredSize(new Dimension(100, 40));
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbcinicio.gridx = 0; gbcinicio.gridy = 2;  // Definindo posição do botão "Sair"
        painelTopo.add(botaoSair, gbcinicio);


        // Botão "Adicionar Livro"
        JButton telaAdicionarLivro = new JButton("Adicionar Livro");
        telaAdicionarLivro.setFont(new Font("Arial", Font.PLAIN, 16));
        telaAdicionarLivro.setPreferredSize(new Dimension(200, 50));
        telaAdicionarLivro.setBackground(new Color(0, 51, 25)); // Verde escuro
        telaAdicionarLivro.setForeground(Color.WHITE);
        telaAdicionarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaAdicionarLivro(); // Abre a tela de adicionar livro
            }
        });
        gbcinicio.gridx = 0; gbcinicio.gridy = 0;  // Definindo posição do botão "Adicionar Livro"
        painelTopo.add(telaAdicionarLivro, gbcinicio);

        JButton DeletarLivro = new JButton("Deletar Livro");
        DeletarLivro.setFont(new Font("Arial", Font.PLAIN, 16));
        DeletarLivro.setPreferredSize(new Dimension(200, 50));
        DeletarLivro.setBackground(new Color(0, 51, 25));
        DeletarLivro.setForeground(Color.WHITE);
        DeletarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaDeletarLivro();
            }
        });

        gbcinicio.gridx = 0; gbcinicio.gridy = 1;
        painelTopo.add(DeletarLivro, gbcinicio);

        painelPrincipal.add(painelTopo);

        add(painelPrincipal, BorderLayout.NORTH);

        setSize(800, 600);  // Tamanho inicial
        setLocationRelativeTo(null);
        setVisible(true);




    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}

//*CRIAR TELA ADICIONAR LIVRO *\\

class TelaAdicionarLivro extends JFrame{

    private final JTextField campoTitulo, campoAutor, campoStatus;
    private final Biblioteca biblioteca;


    public TelaAdicionarLivro(){
        biblioteca = new Biblioteca();

        setTitle("Adicionar Livro");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal com layout GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        // Configurando a borda com título centralizado
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Cadastro de Livro",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz os componentes ocuparem toda a largura
        gbc.gridwidth = 1; // Cada label e campo de texto ocupa uma coluna

        // Rótulo do título
        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(labelTitulo, gbc);

        // Campo de texto do título
        campoTitulo = new JTextField(20);
        gbc.gridx = 1;
        panel.add(campoTitulo, gbc);

        // Rótulo do autor
        JLabel labelAutor = new JLabel("Autor:");
        labelAutor.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(labelAutor, gbc);

        // Campo de texto do autor
        campoAutor = new JTextField(20);
        gbc.gridx = 1;
        panel.add(campoAutor, gbc);

        // Rótulo do status
        JLabel labelStatus = new JLabel("Status:");
        labelStatus.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(labelStatus, gbc);

        // Campo de texto do status
        campoStatus = new JTextField(20);
        gbc.gridx = 1;
        panel.add(campoStatus, gbc);

        // Botão salvar ocupando toda a área horizontal
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoSalvar.setBackground(new Color(0, 51, 25)); // Verde escuro
        botaoSalvar.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2; // Faz o botão ocupar espaço inteiro
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o botão expandir
        panel.add(botaoSalvar, gbc);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = campoTitulo.getText();
                String autor = campoAutor.getText();
                String status = campoStatus.getText();

                if (titulo.isEmpty() || autor.isEmpty() || status.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(!status.equalsIgnoreCase("disponivel") && !status.equalsIgnoreCase("emprestado")){
                    JOptionPane.showMessageDialog(null, "Valor inválido. Insira 'disponível' ou 'emprestado'", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Livro novolivro = new Livro(titulo, autor, status);

                if (biblioteca.verificarLivro(novolivro)){
                    JOptionPane.showMessageDialog(null,"Este livro já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                biblioteca.adicionarLivro(novolivro);

                JOptionPane.showMessageDialog(null,"Livro adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    }

    //* CRIAR TELA DELETAR LIVRO*\\

class TelaDeletarLivro extends JFrame{
    private final Biblioteca biblioteca;
    private final JTable tabelaLivros;
    private final DefaultTableModel modeloTabela;

    public TelaDeletarLivro() {
        biblioteca = new Biblioteca();

        // criar tela
        setTitle("Excluir livro");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //painel principal
        JPanel panel = new JPanel(new BorderLayout());

        //criar tabela
        String[] colunas = {"Título", "Autor", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaLivros = new JTable(modeloTabela);
        carregarLivrosTabela();

        JScrollPane jsp = new JScrollPane(tabelaLivros);
        panel.add(jsp, BorderLayout.CENTER);

        JButton botaoExcluir = new JButton("Excluir livro");
        botaoExcluir.setBackground(new Color(200, 0, 0));
        botaoExcluir.setForeground(Color.WHITE);

        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirLivroSelecionado();
            }
        });
        panel.add(botaoExcluir, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    private void carregarLivrosTabela(){
        modeloTabela.setRowCount(0);
        List<Livro> livros = biblioteca.listarLivros();

        for(Livro livro : livros){
            modeloTabela.addRow(new Object[]{livro.getTitulo(), livro.getAutor(), livro.getStatus()});
        }
    }

    private void excluirLivroSelecionado(){
        int linhaselecionada = tabelaLivros.getSelectedRow();
        if (linhaselecionada != 1){
            String titulo = (String) modeloTabela.getValueAt(linhaselecionada, 0);
            String autor = (String) modeloTabela.getValueAt(linhaselecionada, 1);

            Livro livro = new Livro(titulo, autor, "");
            if (biblioteca.excluirLivro(livro)){
                JOptionPane.showMessageDialog(this,"Livro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarLivrosTabela();
            } else{
                JOptionPane.showMessageDialog(this, "Erro ao excluir livro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

}