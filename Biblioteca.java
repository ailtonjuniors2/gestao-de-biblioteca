import java.time.LocalDate;
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
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public boolean registrarEmprestimo(Usuario usuario, Livro livro) {

        if (livro.getStatus().equals("dispon?vel")) {
            livro.setStatus("emprestado");


            Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.parse("2025-03-20"), LocalDate.parse("2025-04-20"), null);
            emprestimos.add(emprestimo);

            System.out.println("Empr?stimo registrado com sucesso!");
            return true;
        } else {
            System.out.println("O livro j? est? emprestado.");
            return false;
        }
    }
}

