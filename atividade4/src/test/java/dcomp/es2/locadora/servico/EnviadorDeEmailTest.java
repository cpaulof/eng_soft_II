package dcomp.es2.locadora.servico;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dcomp.es2.locadora.builder.AluguelBuilder;
import dcomp.es2.locadora.builder.ClienteBuilder;
import dcomp.es2.locadora.builder.ImovelBuilder;
import dcomp.es2.locadora.builder.LocacaoBuilder;
import dcomp.es2.locadora.erros.ErrorAoEnviarEmailException;
import dcomp.es2.locadora.modelo.Aluguel;
import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.modelo.Imovel;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.repositorio.AluguelRepository;

public class EnviadorDeEmailTest {
    
    private AluguelRepository alugueis;
    private AluguelService aluguelService;
    private EnviadorDeEmail enviadorDeEmail;

    private EntityManager manager;
    private static EntityManagerFactory emf;

    @Mock
    private EmailService emailService;

    @BeforeAll
    public static void init(){
        emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);

        manager = emf.createEntityManager();
		manager.getTransaction().begin();

        alugueis = new AluguelRepository(manager);
        aluguelService = new AluguelService(manager, alugueis);
        enviadorDeEmail = new EnviadorDeEmail(emailService);
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
    void deveNotificarClientesAtrasados(){
        Cliente c1 = ClienteBuilder.umCliente().constroi();
        Cliente c2 = ClienteBuilder.umCliente().constroi();
        Cliente c3 = ClienteBuilder.umCliente().constroi();

        Imovel im1 = ImovelBuilder.umImovel().constroi();
        Imovel im2 = ImovelBuilder.umImovel().constroi();
        Imovel im3 = ImovelBuilder.umImovel().constroi();

        Locacao l1 = LocacaoBuilder.umaLocacao().ativa(true).comInquilino(c1).doImovel(im1).constroi();
        Locacao l2 = LocacaoBuilder.umaLocacao().ativa(true).comInquilino(c2).doImovel(im2).constroi();
        Locacao l3 = LocacaoBuilder.umaLocacao().ativa(true).comInquilino(c3).doImovel(im3).constroi();

        Aluguel al1 = AluguelBuilder.umAluguel().comLocacao(l1).jaPaga().constroi();
        Aluguel al2 = AluguelBuilder.umAluguel().comLocacao(l2).jaPaga().constroi();
        Aluguel al3 = AluguelBuilder.umAluguel().comLocacao(l3).jaPaga().constroi();
        Aluguel al4 = AluguelBuilder.umAluguel().comLocacao(l1).atrasada(10).constroi(); // atrasada
        Aluguel al5 = AluguelBuilder.umAluguel().comLocacao(l2).atrasada(2).constroi();  // atrasada

        //when(emailService.notifica(c1, null);)

        alugueis.salva(al1);
        alugueis.salva(al2);
        alugueis.salva(al3);
        alugueis.salva(al4);
        alugueis.salva(al5);


        
        enviadorDeEmail.notificarClientesAtrasados(aluguelService);

        
        assertDoesNotThrow(()->{
            verify(emailService, times(1)).notifica(c1);
            verify(emailService, times(1)).notifica(c2);
        }); 
    }

    @Test
    void deveLancarExcecaoDeEmail(){
        Cliente c1 = ClienteBuilder.umCliente().constroi();
        Cliente c2 = ClienteBuilder.umCliente().constroi();

        Imovel im1 = ImovelBuilder.umImovel().constroi();
        Imovel im2 = ImovelBuilder.umImovel().constroi();

        Locacao l1 = LocacaoBuilder.umaLocacao().ativa(true).comInquilino(c1).doImovel(im1).constroi();
        Locacao l2 = LocacaoBuilder.umaLocacao().ativa(true).comInquilino(c2).doImovel(im2).constroi();

        Aluguel al1 = AluguelBuilder.umAluguel().comLocacao(l1).atrasada(2).constroi();
        Aluguel al2 = AluguelBuilder.umAluguel().comLocacao(l2).atrasada(2).constroi();

        assertDoesNotThrow(()->{
            doThrow(new ErrorAoEnviarEmailException()).when(emailService).notifica(c1);
        });


        alugueis.salva(al1);
        alugueis.salva(al2);

        enviadorDeEmail.notificarClientesAtrasados(aluguelService);
        // assertThrows(ErrorAoEnviarEmailException.class, ()->{
        //     enviadorDeEmail.notificarClientesAtrasados(aluguelService);
        // });
        assertThrows(ErrorAoEnviarEmailException.class, ()->{
            verify(emailService, times(0)).notifica(c1);
        });

        assertDoesNotThrow(()->{
            verify(emailService, times(1)).notifica(c2);
        });
        
    }

}
