package dcomp.es2.locadora.repositorio;

import dcomp.es2.locadora.builder.ClienteBuilder;
import dcomp.es2.locadora.builder.LocacaoBuilder;
import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.modelo.Locacao;
import net.bytebuddy.asm.Advice.Local;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LocacaoRepositoryTest {

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
	public void deveSalvarLocacao(){
		Cliente cliente = ClienteBuilder.umCliente().comCpf(4444).constroi();
		Locacao locacao = LocacaoBuilder.umaLocacao().comInquilino(cliente).constroi();
		
		clientes.salva(cliente);
		locacoes.salva(locacao);
		Integer id = locacao.getId();

		Locacao buscada = locacoes.buscaId(id);

		assertSame(locacao, buscada);
		assertEquals(4444, buscada.getInquilino().getCpf());
	}

	@Test
	public void deveExcluirLocacao(){
		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();

		//criar locacao
		locacoes.salva(locacao);
		Integer id = locacao.getId();

		//excluir locacao
		locacoes.exclui(locacao);

		// deve ser null
		Locacao buscada = locacoes.buscaId(id);
		assertNull(buscada);
	}

	@Test
	public void deveListarLocacoes(){
		Locacao locacao1 = LocacaoBuilder.umaLocacao().constroi();
		Locacao locacao2 = LocacaoBuilder.umaLocacao().constroi();
		Locacao locacao3 = LocacaoBuilder.umaLocacao().constroi();

		//criar locacoes
		locacoes.salva(locacao1);
		locacoes.salva(locacao2);
		locacoes.salva(locacao3);
	

		//listar locacoes
		List<Locacao> lista =  locacoes.lista();

		// assegurar que o tamanho da lista deve ser 3
		assertEquals(3, lista.size());
	}

	@Test
	public void deveAtualizarLocacao(){
		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();
		
		//criar locacoes
		locacoes.salva(locacao);

		// assegurar que o dia do vencimento eh 10
		assertEquals(10, locacao.getDiaVencimento());


		//Atualizar dia do vencimento e persistir
		locacao.setDiaVencimento(20);
		locacoes.atualiza(locacao);

		// buscar a locacao salva
		Integer id = locacao.getId();
		Locacao buscada = locacoes.buscaId(id);

	
		// assegurar que o dia do vencimento agora eh 20
		assertEquals(20, buscada.getDiaVencimento());
	}


}
