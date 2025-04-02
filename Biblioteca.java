import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Biblioteca {


    private final List<Livro> livros;
    private final List<Usuario> usuarios;
    private final List<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        // cria biblioteca
    }

    public void adicionarLivro(Livro livro) {
        String query = "INSERT INTO livros (titulo, autor, status) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getStatus());

            ps.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }


    public boolean verificarLivro(Livro livro) {
        String query = "SELECT COUNT(*) FROM livros WHERE titulo = ? AND autor = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());

            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar livro: " + e.getMessage());
        }

        return false;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario: " + usuario + " Adicionado com sucesso");
    }

    public boolean registrarEmprestimo(Usuario usuario, Livro livro) {

        if (livro.getStatus().equals("disponivel")) {
            livro.setStatus("emprestado");

            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = dataEmprestimo.plusDays(30);


            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucao, null);
            emprestimos.add(emprestimo);

            System.out.println("Emprestimo registrado com sucesso!");
            return true;
        } else {
            System.out.println("O livro ja foi emprestado.");
            return false;
        }
    }

    public void devolverLivro(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getDataDevolucao() == null) {

                LocalDate dataEstimada = emprestimo.getDataDevolucaoPrevista();
                LocalDate dataDevolucaoReal = LocalDate.now();

                if (dataDevolucaoReal.isAfter(dataEstimada)) {
                    long diasAtraso = dataEstimada.until(dataDevolucaoReal, ChronoUnit.DAYS);
                    System.out.println("A devolucao do livro '" + livro.getTitulo() + "' esta atrasada em " + diasAtraso + " dias.");
                } else {
                    System.out.println("O livro '" + livro.getTitulo() + "' foi devolvido dentro do prazo.");
                }

                livro.setStatus("disponivel");
                emprestimo.setDataDevolucao(dataDevolucaoReal);

                System.out.println("O livro '" + livro.getTitulo() + "' foi devolvido com sucesso!");
                emprestimos.remove(emprestimo);
                return;
            }
        }
        System.out.println("Este livro nao foi encontrado entre os emprestados ou ja foi devolvido.");

    }

    public void consultarLivrosDisponiveis() {
        System.out.println("\n Livros Disponiveis: ");
        boolean encontrou = false;

        for (Livro livro : livros) {
            if (livro.getStatus().equalsIgnoreCase("disponivel")) {
                System.out.println("- " + livro.getTitulo() + " | Autor: " + livro.getAutor());
                encontrou = true;

            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro disponivel.");
        }

    }

    public void consultarLivrosEmprestados() {
        System.out.println("\n Livros emprestados: ");
        if (emprestimos.isEmpty()) {
            System.out.println("Nao ha livros emprestados.");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println("- Livro: " + emprestimo.getLivro().getTitulo()
                        + " | Usu?rio: " + emprestimo.getUsuario().getNome()
                        + " | Data do Empr?stimo: " + emprestimo.getDataEmprestimo());
            }
        }
    }

    public List<Livro> listarLivros() {
        livros.clear();
        String query = "SELECT titulo, autor, status FROM livros";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String status = rs.getString("status");
                livros.add(new Livro(titulo, autor, status));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
        return livros;
    }
    public boolean excluirLivro(Livro livro){
        String query = "DELETE FROM livros WHERE titulo = ? AND autor = ?";

        try (Connection conn = ConexaoMySQL.conectar();
        PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0){
                livros.removeIf(l -> l.getTitulo().equalsIgnoreCase(livro.getTitulo())
                                && l.getAutor().equalsIgnoreCase(livro.getAutor()));
                System.out.println("Livro removido com sucesso.");
                return true;
            } else {
                System.out.println("Livro não encontrado no banco de dados.");
            }

        } catch (SQLException e){
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
        return false;
    }
}

