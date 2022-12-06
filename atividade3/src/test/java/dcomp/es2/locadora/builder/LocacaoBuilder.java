package dcomp.es2.locadora.builder;

import java.time.LocalDate;

import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.modelo.Imovel;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.utils.DataUtils;

public class LocacaoBuilder {

	private Locacao locacao;
	
	
	private LocacaoBuilder() { }

	public static LocacaoBuilder umaLocacao() {
		LocacaoBuilder builder = new LocacaoBuilder();
		
		builder.locacao = new Locacao();
		
		builder.locacao.setAtivo(true);
		builder.locacao.setInquilino(ClienteBuilder.umCliente().constroi());

		builder.locacao.setDataFim(DataUtils.obterDataComDiferencaDias(365));
		builder.locacao.setDataInicio(LocalDate.now());
		builder.locacao.setDiaVencimento(10);
		//builder.locacao.setImovel(ImovelBuilder.umImovel().constroi());
		
		return builder;
	}



	public Locacao constroi() {
		return locacao;
	}

	public LocacaoBuilder comInquilino(Cliente cliente){
		locacao.setInquilino(cliente);
		return this;
	}

	public LocacaoBuilder ativa(Boolean ativo){
		locacao.setAtivo(ativo);
		return this;
	}

	public LocacaoBuilder doImovel(Imovel imovel){
		locacao.setImovel(imovel);
		return this;
	}

}
