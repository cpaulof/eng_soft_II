package atvd2.model;

import java.time.LocalDate;

public class Emprestimo {
    private Usuario usuario;
    private Livro[] livros;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private Double valor;

    //getters setters

    public Emprestimo(Usuario usuario, Livro... livros) {
        this.usuario = usuario;
        this.livros = livros;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevista = dataEmprestimo.plusDays(7);
        this.dataDevolucao = null;
        valor = livros.length * 5.0;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return this.dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro[] getLivros(){
        return this.livros;
    }

}
