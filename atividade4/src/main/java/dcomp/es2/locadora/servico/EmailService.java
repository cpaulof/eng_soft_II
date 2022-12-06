package dcomp.es2.locadora.servico;

import dcomp.es2.locadora.erros.ErrorAoEnviarEmailException;
import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.utils.EmailMessage;

public interface EmailService {
	
	public void notifica(Cliente cliente ) throws ErrorAoEnviarEmailException; 

}
