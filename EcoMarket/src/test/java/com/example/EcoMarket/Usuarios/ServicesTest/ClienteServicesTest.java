package com.example.EcoMarket.Usuarios.ServicesTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Usuarios.model.Cliente;
import Usuarios.repository.ClienteRepository;
import Usuarios.service.ClienteService;

@SpringBootTest
public class ClienteServicesTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(1L, "Juan Perez", "juan.perez@gmail.com", "Calle Falsa 123", "555-1234")));
        List<Cliente> clientes = clienteService.obtenerTodos();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente(id, "Juan Perez", "juan.perez@gmail.com", "Calle Falsa 123", "555-1234");

        when(clienteRepository.findById(id)).thenReturn(java.util.Optional.of(cliente));
        Cliente foundCliente = clienteService.ObtenerPorId(id);
        assertNotNull(foundCliente);
        assertEquals(id, foundCliente.getId());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente(1L, "Juan Perez", "juan.perez@gmail.com", "Calle Falsa 123", "555-1234");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.guardar(cliente);
        assertNotNull(savedCliente);
        assertEquals(savedCliente.getId(), savedCliente.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(clienteRepository).deleteById(id);
        clienteService.eliminar(id);

        
        verify(clienteRepository, new Times(1)).deleteById(id);
    }
}
