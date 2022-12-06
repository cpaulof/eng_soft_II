package dcomp.es2.locadora.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class DAOGenerico<T> {

    private EntityManager manager;
    private Class<T> pclass;

    protected DAOGenerico(EntityManager manager){
        this.manager = manager;
    }

    protected DAOGenerico(EntityManager manager, Class<T> pclass){
        this(manager);
        this.pclass = pclass;
    }

    public void salva(T obj){
        manager.persist(obj);
    }
    public void atualiza(T obj){
        manager.merge(obj);
    }
    public void exclui(T obj){
        manager.remove(obj);
    }
    public T buscaId(Integer id){
        return manager.find(pclass, id);
    }
    public List<T> lista(){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(pclass);
        query.from(pclass);
        return manager.createQuery(query).getResultList();
    }
}
