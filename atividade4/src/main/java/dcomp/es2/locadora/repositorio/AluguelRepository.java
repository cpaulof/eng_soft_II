package dcomp.es2.locadora.repositorio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import dcomp.es2.locadora.modelo.Aluguel;

public class AluguelRepository extends DAOGenerico<Aluguel>{

    private EntityManager manager;

    public AluguelRepository(EntityManager manager) {
        super(manager, Aluguel.class);
        this.manager = manager;
    }
    
    public List<Aluguel> listaAtrasados(){
        LocalDate agora = LocalDate.now();
        return manager.createQuery(
            "SELECT a FROM Aluguel a WHERE a.dataVencimento < :agora", Aluguel.class)
            .setParameter("agora", agora)
            .getResultList();
    }
}
