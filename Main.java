public class Main{
    public static void main(String[] args) {
        Livro livro = new Livro();
        livro.setAnoPublicacao(2018);
        livro.setAutor("Rick Riordan");
        livro.setTitulo("As Provações de Apolo: O Labirinto de Fogo");
        livro.setStatus("Disponível");

        System.out.println(livro);
    }
}
