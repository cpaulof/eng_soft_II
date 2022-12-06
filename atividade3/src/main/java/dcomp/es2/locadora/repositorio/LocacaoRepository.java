package dcomp.es2.locadora.repositorio;

import javax.persistence.EntityManager;


import dcomp.es2.locadora.modelo.Locacao;

public class LocacaoRepository extends DAOGenerico<Locacao>{

    private EntityManager manager;

    public LocacaoRepository(EntityManager manager) {
        super(manager, Locacao.class);
        this.manager = manager;
    }   
}
