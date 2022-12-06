package dcomp.es2.locadora.builder;

import dcomp.es2.locadora.modelo.Imovel;

public class ImovelBuilder {
    private Imovel imovel;

    ImovelBuilder(){}

    public static ImovelBuilder umImovel(){
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        imovelBuilder.imovel = new Imovel();
        Imovel i = imovelBuilder.imovel;
        i.setBairro("Monte Castelo");
        i.setBanheiros(2);
        i.setCep(65035000);
        i.setDormitorios(3);
        i.setEndereco("Av. Joao da Silva , numero 1");
        i.setMetragem(120);
        i.setSuites(1);
        i.setTipoImovel("residencia");
        i.setObs("Nao permite pets");
        i.setVagasGaragem(2);
        i.setValorAluguelSugerido(2200);
        return imovelBuilder;
    }

    public ImovelBuilder comValorAluguel(Double valor){
        imovel.setValorAluguelSugerido(valor);
        return this;
    }

    public ImovelBuilder comBairro(String bairro){
        imovel.setBairro(bairro);
        return this;
    }

    public ImovelBuilder doTipo(String tipo){
        imovel.setTipoImovel(tipo);
        return this;
    }

    public Imovel constroi(){
        return imovel;
    }
}
