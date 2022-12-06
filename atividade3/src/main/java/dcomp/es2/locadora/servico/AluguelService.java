package dcomp.es2.locadora.servico;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Aluguel;

public class AluguelService {

    private EntityManager manager;

    public AluguelService(EntityManager manager){
        this.manager = manager;
    }

    public Double pagarAluguel(Aluguel aluguel){
        aluguel.setValorPago(null);
        return 0.;
    }
}
