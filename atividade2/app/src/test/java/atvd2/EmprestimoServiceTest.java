package atvd2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import atvd2.controller.EmprestimoService;
import atvd2.model.Emprestimo;
import atvd2.model.Livro;
import atvd2.model.Usuario;

public class EmprestimoServiceTest {
    
    @Test
    void deveEmprestarLivro(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 2");
        Usuario usuario = new Usuario("Ricado", "2022-0001");

        // checar que o livro nao esta reservado
        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.addLivro(livro);
        emprestimoService.emprestar(usuario, livro);

        // checar que o livro esta emprestado
        assertEquals(true, livro.isEmprestado());
    }

    @Test
    void emprestarLivroReservado(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 2");
        Usuario usuario = new Usuario("Ricado", "2022-0001");

        // checar que o livro nao esta reservado
        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.reservar(livro);

        emprestimoService.addLivro(livro);
        emprestimoService.emprestar(usuario, livro);

        // checar que o livro esta emprestado
        assertFalse(livro.isEmprestado());
        assertTrue(livro.isReservado());
    }

    @Test
    void dataPrevistaCorreta(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 2");
        Usuario usuario = new Usuario("Ricado", "2022-0001");

        

        emprestimoService.addLivro(livro);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro);

        LocalDate agora = LocalDate.now();

        assertEquals(agora.plusDays(7), emprestimo.getDataPrevista());
        
    }

    @Test
    void usuarioCom3Emprestimos(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro1 = new Livro("Carlos Pereira", "Titulo Generico vol. 1");
        Livro livro2 = new Livro("Carlos Pereira", "Titulo Generico vol. 2");
        Livro livro3 = new Livro("Carlos Pereira", "Titulo Generico vol. 3");
        Usuario usuario = new Usuario("Ricado", "2022-0001");

        // checar que o livro nao esta reservado
        assertFalse(livro1.isReservado());
        assertFalse(livro1.isEmprestado());
        assertFalse(livro2.isReservado());
        assertFalse(livro2.isEmprestado());
        assertFalse(livro3.isReservado());
        assertFalse(livro3.isEmprestado());

        emprestimoService.addLivro(livro1);
        emprestimoService.addLivro(livro2);
        emprestimoService.addLivro(livro3);
        emprestimoService.emprestar(usuario, livro1, livro2, livro3);

        // checar que o livro esta emprestado
        assertTrue(livro1.isEmprestado());
        assertTrue(livro2.isEmprestado());
        assertTrue(livro3.isEmprestado());
    }

    @Test
    void usuarioCom4Emprestimos(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro1 = new Livro("Carlos Pereira", "Titulo Generico vol. 1");
        Livro livro2 = new Livro("Carlos Pereira", "Titulo Generico vol. 2");
        Livro livro3 = new Livro("Carlos Pereira", "Titulo Generico vol. 3");
        Livro livro4 = new Livro("Carlos Pereira", "Titulo Generico vol. 4");
        Usuario usuario = new Usuario("Ricado", "2022-0001");

        // checar que o livro nao esta reservado
        assertFalse(livro1.isReservado());
        assertFalse(livro1.isEmprestado());
        assertFalse(livro2.isReservado());
        assertFalse(livro2.isEmprestado());
        assertFalse(livro3.isReservado());
        assertFalse(livro3.isEmprestado());
        assertFalse(livro4.isReservado());
        assertFalse(livro4.isEmprestado());

        emprestimoService.addLivro(livro1);
        emprestimoService.addLivro(livro2);
        emprestimoService.addLivro(livro3);
        emprestimoService.addLivro(livro4);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro1, livro2, livro3, livro4);
        

        assertEquals(null, emprestimo);

        // checar que os livros nao estao emprestados
        assertFalse(livro1.isEmprestado());
        assertFalse(livro2.isEmprestado());
        assertFalse(livro3.isEmprestado());
        assertFalse(livro4.isEmprestado());
    }

    @Test
    void devolverAntesDaDataPrevista(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 1");

        Usuario usuario = new Usuario("Ricado", "2022-0001");

        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.addLivro(livro);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro);

        assertNotNull(emprestimo);
        assertTrue(livro.isEmprestado());

        double valorEsperado = 5.0;

        double valorAPagar = emprestimoService.devolver(emprestimo, LocalDate.now());

        assertEquals(valorEsperado, valorAPagar);
    }

    @Test
    void devolverNaDataPrevista(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 1");

        Usuario usuario = new Usuario("Ricado", "2022-0001");

        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.addLivro(livro);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro);

        assertNotNull(emprestimo);
        assertTrue(livro.isEmprestado());

        double valorEsperado = 5.0;

        double valorAPagar = emprestimoService.devolver(emprestimo, emprestimo.getDataPrevista());

        assertEquals(valorEsperado, valorAPagar);
    }

    @Test
    void devolverUmDiaDepois(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 1");

        Usuario usuario = new Usuario("Ricado", "2022-0001");

        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.addLivro(livro);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro);

        assertNotNull(emprestimo);
        assertTrue(livro.isEmprestado());

        double valorEsperado = 5.4;

        double valorAPagar = emprestimoService.devolver(emprestimo, emprestimo.getDataPrevista().plusDays(1));

        assertEquals(valorEsperado, valorAPagar);
    }

    @Test
    void devolverTrintaDiasDepois(){
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = new Livro("Carlos Pereira", "Titulo Generico vol. 1");

        Usuario usuario = new Usuario("Ricado", "2022-0001");

        assertFalse(livro.isReservado());
        assertFalse(livro.isEmprestado());

        emprestimoService.addLivro(livro);
        Emprestimo emprestimo = emprestimoService.emprestar(usuario, livro);

        assertNotNull(emprestimo);
        assertTrue(livro.isEmprestado());

        double valorEsperado = 8.0;

        double valorAPagar = emprestimoService.devolver(emprestimo, emprestimo.getDataPrevista().plusDays(30));

        assertEquals(valorEsperado, valorAPagar);
    }


}
