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
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
        System.out.println("Livro: "+ livro + " Adicionado com sucesso");
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
        System.out.println("Usuario: "+ usuario + " Adicionado com sucesso");
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

    public void devolverLivro(Livro livro){
        for (Emprestimo emprestimo : emprestimos){
            if (emprestimo.getLivro().equals(livro) && emprestimo.getDataDevolucao() == null){
                
                LocalDate dataEstimada = emprestimo.getDataDevolucaoPrevista();
                LocalDate dataDevolucaoReal = LocalDate.now();

                if (dataDevolucaoReal.isAfter(dataEstimada)){
                    long diasAtraso = dataEstimada.until(dataDevolucaoReal, ChronoUnit.DAYS);
                    System.out.println("A devolu??o do livro '" + livro.getTitulo() + "' est? atrasada em " + diasAtraso + " dias.");
                } else{
                    System.out.println("O livro '" + livro.getTitulo() + "' foi devolvido dentro do prazo.");
                }
                
                livro.setStatus("disponivel");
                emprestimo.setDataDevolucao(dataDevolucaoReal);

                System.out.println("O livro '" + livro.getTitulo() + "' foi devolvido com sucesso!");
                emprestimos.remove(emprestimo);
                return;
            }
        }
        System.out.println("Este livro n?o foi encontrado entre os emprestados ou j? foi devolvido.");

    }

    public void consultarLivrosDisponiveis(){
        System.out.println("\n Livros Dispon?veis: ");
        boolean encontrou = false;

        for (Livro livro : livros){
            if (livro.getStatus().equalsIgnoreCase("disponivel")){
                System.out.println("- " + livro.getTitulo() + " | Autor: " + livro.getAutor());
                encontrou = true;

            }
        }
        if (!encontrou){
            System.out.println("Nenhum livro dispon?vel.");
        }

    }

    public void consultarLivrosEmprestados(){
        System.out.println("\n Livros emprestados: ");
        if(emprestimos.isEmpty()){
            System.out.println("Nao ha livros emprestados.");
        } else {
            for(Emprestimo emprestimo : emprestimos){
                System.out.println("- Livro: " + emprestimo.getLivro().getTitulo()
                + " | Usu?rio: " + emprestimo.getUsuario().getNome()
                + " | Data do Empr?stimo: " + emprestimo.getDataEmprestimo());
            }
        }
    }
}

