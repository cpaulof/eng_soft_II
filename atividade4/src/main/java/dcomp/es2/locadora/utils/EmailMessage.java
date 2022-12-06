package dcomp.es2.locadora.utils;

import dcomp.es2.locadora.modelo.Aluguel;
import dcomp.es2.locadora.modelo.Cliente;

public class EmailMessage {
    private String message;

    public void fromAluguelAtrasado(Aluguel aluguel){
        Cliente cliente = aluguel.getLocacao().getInquilino();
        message = "Senhor(a) " + cliente.getNome() + 
        "Informamos que seu aluguel esta atrasado etc etc etc";
    }
    
    public String getMessage(){
        return message;
    }
}
