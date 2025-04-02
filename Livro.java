    public class Livro {
        private String titulo, autor, status;
        private int anoPublicacao;

        public Livro(String titulo, String autor, String status) {
            this.titulo = titulo;
            this.autor = autor;
            this.status = status;
        }

        // getter para retornar titulo do livro
        public String getTitulo() {
            return titulo;
        }
        // getter para 'setar' titulo do livro
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        // getter para retornar nome do autor
        public String getAutor(){
            return autor;
        }
        // setter para 'setar' nome do autor
        public void setAutor(String autor){
            this.autor = autor;
        }
        // getter para retornar ano de publicacao
        public int anoPublicacao(){
            return anoPublicacao;
        }
        // setter para 'setar' ano de publicacao
        public void setAnoPublicacao(int anoPublicacao){
            this.anoPublicacao = anoPublicacao;
        }
        // getter para retornar status do livro
        public String getStatus(){
            return status;
        }
        // setter parar 'setar' status do livro
        public void setStatus(String status){
            this.status = status;
        }

        @Override
        public String toString(){
            return "Livro (" +
            "Titulo:'" + titulo + '\'' +
            " Autor:'" + autor + '\'' +
            " Ano de Publicacao:'" + anoPublicacao + '\'' +
            " Status:'" + status + '\'' +
            ")";
        }

}

