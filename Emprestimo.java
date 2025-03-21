import java.time.LocalDate;

public class Emprestimo {
    private final Livro livro;
    private final Usuario usuario;
    private LocalDate dataEmprestimo, dataDevolucaoPrevista, dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucao = dataDevolucao;
    }

    public boolean verificarDataDevolucao(){
        if (dataDevolucao == null){
            return false;
        }

        return !dataDevolucao.isAfter(dataDevolucaoPrevista);
    }

    public LocalDate getDataEmprestimo(){
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo){
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao(){
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao){
        if (dataDevolucao.isBefore(dataEmprestimo)){
            throw new IllegalArgumentException("O livro nao pode ser devolvido antes do emprestimo.");
        }

        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolucaoPrevista(){
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista){
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    @Override
    public String toString(){
        return "Emprestimo (" +
        "Livro:'" + livro.getTitulo() + '\'' +
        " Usuario:'" + usuario.getNome() + '\'' +
        " Data do Emprestimo:'" + dataEmprestimo + '\'' +
        " Data de Devolucao Prevista:'" + dataDevolucaoPrevista + '\'' +
        " Data de Devolucao:'" + dataDevolucao + '\'' +
        ")";
    }
}
