package dcomp.es2.locadora.servico;


import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Imovel;
import dcomp.es2.locadora.repositorio.ImovelRepository;

public class ImovelService {

    private EntityManager manager;
    
    private ImovelRepository imoveis;

    public ImovelService(EntityManager manager){
        this.manager = manager;
    }

    public List<Imovel> buscarImoveisDisponiveis(){
        return manager.createQuery(
            "SELECT i from Imovel i, Locacao l WHERE i.id=l.id and l.ativo=false", Imovel.class).getResultList();
    }

    public List<Imovel> buscarPorBairro(String bairro){
        List<Imovel> imoveisDisp = buscarImoveisDisponiveis();
        List<Imovel> porBairro = new ArrayList<Imovel>();
        imoveisDisp.forEach(imovel->{
            if(imovel.getBairro().equals(bairro))
                porBairro.add(imovel);
        });

        return porBairro;
    }

    public List<Imovel> buscarPorValor(Double valor){
        List<Imovel> imoveisDisp = buscarImoveisDisponiveis();
        List<Imovel> porValor = new ArrayList<Imovel>();
        imoveisDisp.forEach(imovel->{
            if(imovel.getValorAluguelSugerido() <= valor)
                porValor.add(imovel);
        });

        return porValor;
    }

    
}
