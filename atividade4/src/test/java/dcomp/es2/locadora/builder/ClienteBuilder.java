package dcomp.es2.locadora.builder;

import java.time.LocalDate;

import dcomp.es2.locadora.modelo.Aluguel;
import dcomp.es2.locadora.modelo.Cliente;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.utils.DataUtils;

public class ClienteBuilder {
    private Cliente cliente;

    ClienteBuilder(){}

    public static ClienteBuilder umCliente(){
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        clienteBuilder.cliente = new Cliente();
        Cliente c = clienteBuilder.cliente;
        c.setCpf(1234567);
        c.setNome("Joao da Silva");
        c.setDataNascimento(DataUtils.obterData(1, 3, 2000));
        c.setEmail("joao@dasilva.com.br");
        c.setTelefone1(111222333);
        return clienteBuilder;
    }

    public ClienteBuilder comNome(String nome){
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCpf(Integer cpf){
        cliente.setCpf(cpf);
        return this;
    }

    public ClienteBuilder comFone1(Integer fone){
        cliente.setTelefone1(fone);
        return this;
    }

    public ClienteBuilder comFone2(Integer fone){
        cliente.setTelefone2(fone);
        return this;
    }

    public ClienteBuilder comNascimento(LocalDate data){
        cliente.setDataNascimento(data);
        return this;
    }

    public ClienteBuilder comNascimento(Integer dia, Integer mes, Integer ano){
        cliente.setDataNascimento(DataUtils.obterData(dia, mes, ano));
        return this;
    }

    public ClienteBuilder comEmail(String email){
        cliente.setEmail(email);
        return this;
    }

    public Cliente constroi(){
        return cliente;
    }
}
