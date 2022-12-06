package atvd2.model;

public class Usuario {
    private String nome;
    private String matricula;
    private boolean emDebito;



    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.emDebito = false;
    }


    public String getNome() {
        return this.nome;
    }

    public String getMatricula() {
        return this.matricula;
    }


    public boolean isEmDebito() {
        return this.emDebito;
    }

    public boolean getEmDebito() {
        return this.emDebito;
    }

    public void setEmDebito(boolean emDebito) {
        this.emDebito = emDebito;
    }

}

    
