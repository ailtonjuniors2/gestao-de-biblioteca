public class Usuario {
    private String nome, email;
    private int id, telefone;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if (id > 99999){
            throw new IllegalArgumentException("ID inv?lido. O ID n?o pode ter mais que 5 d?gitos");
        } else{
            this.id = id;
        }
    }

    public int getTelefone(){
        return telefone;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "Usuario (" +
        "Nome:'" + nome + '\'' +
        " E-Mail:'" + email + '\'' +
        " Identificacao'" + id + '\'' +
        " Telefone:'" + telefone + '\'' +
        ")";
    }
}
