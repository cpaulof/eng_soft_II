package dcomp.es2.locadora.repositorio;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Imovel;

public class ImovelRepository extends DAOGenerico<Imovel>{

    private EntityManager manager;

    public ImovelRepository(EntityManager manager) {
        super(manager, Imovel.class);
        this.manager = manager;
    }   
}
