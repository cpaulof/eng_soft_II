package dcomp.es2.locadora.erros;

public class ErrorAoEnviarEmailException extends Exception{
    
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "Erro ao enviar email!";
    }
}
