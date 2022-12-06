package atvd2.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import atvd2.model.*;


public class EmprestimoService {
    private int MAX_EMPRESTIMOS = 3;

    private List<Emprestimo> emprestimos;
    //private List<Usuario> usuarios;
    private List<Livro> livros;

    public EmprestimoService(){
        emprestimos = new ArrayList<Emprestimo>();
        //usuarios = new ArrayList<Usuario>();
        livros = new ArrayList<Livro>();
    }

    public void addLivro(Livro livro){
        this.livros.add(livro);
    }
    public List<Emprestimo> consultarEmprestimosPorUsuario(Usuario usuario){
        List<Emprestimo> emprestimosPorUsuario = new ArrayList<Emprestimo>();
        emprestimosPorUsuario.forEach(e->{
            if(e.getUsuario().equals(usuario)){
                emprestimosPorUsuario.add(e);
            }
        });
        return emprestimosPorUsuario;
    }

    public int checarEmprestimosUsuario(Usuario usuario){
        // calcula a quantidade de emprestimos ativo pelo usuario e 
        // checa se o usuario pode realizar um novo emprestimo 
        // (quantidade de livros que pode emprestar)

        List<Emprestimo> emprestimosFeitos = consultarEmprestimosPorUsuario(usuario);
        int emprestimosAtivos = 0;
        for(Emprestimo emprestimo: emprestimosFeitos){
            if(emprestimo.getDataDevolucao()==null)
                emprestimosAtivos++;
        }
        int emprestimosRestantes = MAX_EMPRESTIMOS - emprestimosAtivos;
        return emprestimosRestantes;
    }

    public void reservar(Livro livro){
        livro.setReservado(true);
    }


    public Emprestimo emprestar(Usuario usuario, Livro... livros){
        for(Livro livro: livros){
            if(!this.livros.contains(livro) || livro.isEmprestado() || livro.isReservado()){
                System.out.println("Livro nao encontrado ou nao disponivel!!!");
                return null;
            }
            
        }
        
        int emprestimosRestantes = checarEmprestimosUsuario(usuario);
        if(emprestimosRestantes < livros.length){
            System.out.println("Usuario nao pode emprestar essa quantidade de livro(s)!!!");
            return null;
        }

        for(Livro livro: livros)
            livro.setEmprestado(true);
        
        Emprestimo emprestimo = new Emprestimo(usuario, livros);
        this.emprestimos.add(emprestimo);
        return emprestimo;
    }

    public double devolver(Emprestimo emprestimo, LocalDate dev){
        int qtdLivros = emprestimo.getLivros().length;


        LocalDate prev = emprestimo.getDataPrevista();
        //LocalDate dev = LocalDate.now();
        Period periodo = Period.between(prev, dev);
        long months = periodo.getMonths();
        long days = periodo.getDays() + months*30;
        days = dev.isBefore(prev)? 0: days; // devolvendo antes do prazo

        emprestimo.setDataDevolucao(dev);

        double multa = 0.4 * days * qtdLivros;
        double valorBase = 5.0 * qtdLivros;
        multa = multa >= valorBase*0.6?valorBase*0.6:multa; // limitar ao valor maximo
        System.out.println("Devolver: dias:"+days+"  Multa: "+multa+" months: "+months);
        double valorTotal = valorBase + multa;

        return valorTotal;

    }

}
