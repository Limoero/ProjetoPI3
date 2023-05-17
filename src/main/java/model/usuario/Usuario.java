package model.usuario;
public abstract class Usuario {
    private String email;
    private String senha;


    
    public Usuario() {
    }



    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }


    
    public boolean validaLogin(String email, String senha){
       if (this.email.equals(email) || this.senha.equals(senha)) return true;
       
        return false;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getSenha() {
        return senha;
    }



    public void setSenha(String senha) {
        this.senha = senha;
    }

}