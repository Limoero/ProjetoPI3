package model.usuario;

public class Cliente extends Usuario {
    private String nome;
    private String cpf;



    public Cliente() {

    }


    public Cliente(String nome, String cpf, String email, String senha) {
        super(email, senha);
        this.nome = nome;
        this.cpf = cpf;


    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }






    



}
