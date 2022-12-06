package atvd2.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String autor;
    private String titulo;
    private boolean emprestado;
    private boolean reservado;


    public Livro(String autor, String titulo) {
        this.autor = autor;
        this.titulo = titulo;
        this.emprestado = false;
        this.reservado = false;
    
    }


    

    // public List<Emprestimo> consultarEmprestimosPorUsuario(Usuario usuario){
    //     List<Emprestimo> emprestimosPorUsuario = new ArrayList<Emprestimo>();
    //     for(Emprestimo emprestimoUsuario: emprestimosPorUsuario){
    //         if(emprestimoUsuario.getUsuario().equals(usuario)){
    //             emprestimosPorUsuario.add(emprestimoUsuario);
    //         }
    //     }
    //     return emprestimosPorUsuario;
    // }

    // //getters e setters
    // public String getAutor() {
    //     return this.autor;
    // }

    // public void setAutor(String autor) {
    //     this.autor = autor;
    // }

    // public String getTitulo() {
    //     return this.titulo;
    // }

    // public void setTitulo(String titulo) {
    //     this.titulo = titulo;
    // }

    public boolean isEmprestado() {
        return this.emprestado;
    }

    public boolean getEmprestado() {
        return this.emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean isReservado() {
        return this.reservado;
    }

    public boolean getReservado() {
        return this.reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    // public List<Emprestimo> getHistorico() {
    //     return this.historico;
    // }

    // public void setHistorico(List<Emprestimo> historico) {
    //     this.historico = historico;
    // }


}
