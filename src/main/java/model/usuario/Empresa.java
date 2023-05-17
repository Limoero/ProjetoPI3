package model.usuario;

public class Empresa extends Usuario {
    private String nome;
    private String cnpj;




    public Empresa() {

    }

    public Empresa(String email, String senha, String nome, String cnpj) {
        super(email, senha);
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    
    }