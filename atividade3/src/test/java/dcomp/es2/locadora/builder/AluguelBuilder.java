package dcomp.es2.locadora.builder;

import java.time.LocalDate;

import dcomp.es2.locadora.modelo.Aluguel;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.utils.DataUtils;

public class AluguelBuilder {
    private Aluguel aluguel;

    AluguelBuilder(){}

    public static AluguelBuilder umAluguel(){
        AluguelBuilder aluguelBuilder = new AluguelBuilder();
        aluguelBuilder.aluguel = new Aluguel();
        aluguelBuilder.aluguel.setObs("abcde");
        return aluguelBuilder;
    }

    public AluguelBuilder comLocacao(Locacao locacao){
        aluguel.setLocacao(locacao);
        return this;
    }

    public AluguelBuilder jaPaga(){
        aluguel.setDataPagamento(LocalDate.now());
        aluguel.setValorPago(aluguel.getLocacao().getValorAluguel());
        return this;
    }

   

    public Aluguel constroi(){
        return aluguel;
    }
}
