package dcomp.es2.locadora.servico;

import org.junit.jupiter.api.*;

import dcomp.es2.locadora.builder.ImovelBuilder;
import dcomp.es2.locadora.builder.LocacaoBuilder;
import dcomp.es2.locadora.modelo.Imovel;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.repositorio.ClienteRepository;
import dcomp.es2.locadora.repositorio.ImovelRepository;
import dcomp.es2.locadora.repositorio.LocacaoRepository;
import dcomp.es2.locadora.servico.ImovelService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ImovelServiceTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;

	private LocacaoRepository locacoes;
	private ImovelRepository imoveis;
	private ClienteRepository clientes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}
	
	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		locacoes = new LocacaoRepository(manager ); 
		imoveis = new ImovelRepository(manager ); 
		clientes = new ClienteRepository(manager);
		
	}
	
	@AfterEach
	public void depois() {
		manager.getTransaction().rollback();
	}
	
	@AfterAll
	public static void fim() {
		emf.close();
	}

    @Test
    void deveListarAPsDisponiveisPorBairro(){
        /*Dado um bairro recuperar todos os imóveis do tipo “Apartamento” que
        estão disponíveis (a coluna ativo=false na tabela Locacao). */

        Imovel imovel1 = ImovelBuilder.umImovel()
        .doTipo("residencia")
        .comBairro("Monte Castelo").constroi();

        Imovel imovel2 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comBairro("Monte Castelo").constroi();
        
        Imovel imovel3 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comBairro("Cohab").constroi();

        Imovel imovel4 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comBairro("Monte Castelo").constroi();

        Locacao loc1 = LocacaoBuilder.umaLocacao().doImovel(imovel1).ativa(false).constroi();
        Locacao loc2 = LocacaoBuilder.umaLocacao().doImovel(imovel2).ativa(false).constroi();
        Locacao loc3 = LocacaoBuilder.umaLocacao().doImovel(imovel3).ativa(false).constroi();
        Locacao loc4 = LocacaoBuilder.umaLocacao().doImovel(imovel4).ativa(true).constroi();

        locacoes.salva(loc1);
        locacoes.salva(loc2);
        locacoes.salva(loc3);
        locacoes.salva(loc4);
        // imoveis que devem satisfazer a busca: imovel 2
        
        ImovelService imService = new ImovelService(manager);
        
        List<Imovel> listaAPs = new ArrayList<Imovel>();
        imService.buscarPorBairro("Monte Castelo").forEach(imovel->{
            if(imovel.getTipoImovel().equals("apartamento"))
                listaAPs.add(imovel);
        });

        assertEquals(1, listaAPs.size());

    }

    /*Dado um limite de preço, recuperar todos os imóveis que estão
    disponíveis, com valor do aluguel (valor_aluguel_sugerido)
    igual ou inferior ao valor informado. */
    @Test
    void deveListarImoveisDisponiveisPorValor(){
        // valor de referencia sera 2000

        Imovel imovel1 = ImovelBuilder.umImovel()
        .doTipo("residencia")
        .comValorAluguel(1500.)
        .comBairro("Monte Castelo").constroi();

        Imovel imovel2 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comValorAluguel(2500.)
            .comBairro("Monte Castelo").constroi();
        
        Imovel imovel3 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comValorAluguel(1000.)
            .comBairro("Cohab").constroi();

        Imovel imovel4 = ImovelBuilder.umImovel()
            .doTipo("apartamento")
            .comValorAluguel(900.)
            .comBairro("Monte Castelo").constroi();

        Locacao loc1 = LocacaoBuilder.umaLocacao().doImovel(imovel1).ativa(false).constroi();
        Locacao loc2 = LocacaoBuilder.umaLocacao().doImovel(imovel2).ativa(false).constroi();
        Locacao loc3 = LocacaoBuilder.umaLocacao().doImovel(imovel3).ativa(false).constroi();
        Locacao loc4 = LocacaoBuilder.umaLocacao().doImovel(imovel4).ativa(true).constroi();

        locacoes.salva(loc1);
        locacoes.salva(loc2);
        locacoes.salva(loc3);
        locacoes.salva(loc4);

        // imoveis que devem satisfazer a busca: imovel 1 e 3.

        ImovelService imService = new ImovelService(manager);
        
        List<Imovel> listaImoveis =  imService.buscarPorValor(2000.);

        assertEquals(2, listaImoveis.size());


    }
	

}
