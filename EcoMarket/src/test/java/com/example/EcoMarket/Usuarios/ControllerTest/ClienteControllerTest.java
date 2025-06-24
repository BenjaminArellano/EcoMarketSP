package com.example.EcoMarket.Usuarios.ControllerTest;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import Usuarios.controller.ClienteController;
import Usuarios.model.Cliente;
import Usuarios.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Perez");
        cliente.setCorreo("juan.perez@gmail.com");
        cliente.setDireccionEnvio("Calle Falsa 123");
        cliente.setTelefono("1234567890");

    }

    @Test
    void testGetAllProductos() throws Exception {
        when(clienteService.obtenerTodos()).thenReturn(List.of(cliente));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/clientes"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("Juan Perez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].correo").value("juan.perez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].direccionEnvio").value("Calle Falsa 123"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].telefono").value("1234567890"));
        
    }

    @Test
    void testGetClienteById() throws Exception {
        when(clienteService.ObtenerPorId(1L)).thenReturn(cliente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("juan.perez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.direccionEnvio").value("Calle Falsa 123"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("1234567890"));
    }

    @Test
    void testCreateCliente() throws Exception {
        when(clienteService.guardar(cliente)).thenReturn(cliente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/clientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("juan.perez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.direccionEnvio").value("Calle Falsa 123"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("1234567890"));

    }

    @Test
    void testUpdateCliente() throws Exception {
        cliente.setNombre("Juan Perez Actualizado");
        when(clienteService.guardar(cliente)).thenReturn(cliente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/clientes/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Juan Perez Actualizado"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("juan.perez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.direccionEnvio").value("Calle Falsa 123"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("1234567890"));   
    }

    @Test
    void testDeleteCliente() throws Exception {
        Long id = 1L;

        doNothing().when(clienteService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/clientes/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        
        verify(clienteService, new Times(1)).eliminar(id);
    }

}
