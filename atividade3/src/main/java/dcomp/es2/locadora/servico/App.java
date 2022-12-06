package dcomp.es2.locadora.servico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.repositorio.ClienteRepository;

public class App {
    
	private static EntityManagerFactory emf;

    public static void main(String[] args){
        emf = Persistence.createEntityManagerFactory("locadoraPU_test");
        final EntityManager manager = emf.createEntityManager();

		manager.getTransaction().begin();
		
		ClienteRepository clientes = new ClienteRepository(manager);

        Cliente cliente = new Cliente();
        cliente.setNome("Joao");

        clientes.salva(cliente);

        manager.getTransaction().commit();
    }

}
