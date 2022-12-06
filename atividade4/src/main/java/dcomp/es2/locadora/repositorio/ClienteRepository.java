package dcomp.es2.locadora.repositorio;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Cliente;

public class ClienteRepository extends DAOGenerico<Cliente>{

    private EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        super(manager, Cliente.class);
        this.manager = manager;
    }   
}
