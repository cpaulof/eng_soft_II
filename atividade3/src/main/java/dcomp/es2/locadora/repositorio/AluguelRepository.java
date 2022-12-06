package dcomp.es2.locadora.repositorio;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Aluguel;

public class AluguelRepository extends DAOGenerico<Aluguel>{

    private EntityManager manager;

    public AluguelRepository(EntityManager manager) {
        super(manager, Aluguel.class);
        this.manager = manager;
    }   
}
