package com.example.EcoMarket.Pedidos.ControllerTest;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import Pedidos.controller.EnvioController;
import Pedidos.model.Envio;
import Pedidos.model.Pedido;
import Pedidos.service.EnvioService;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnvioService envioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Envio envio;
    @BeforeEach
    void setup() {
        Pedido pedido = mock(Pedido.class);

        envio = new Envio();
        envio.setId(1L);
        envio.setDireccion("123 Main St");
        envio.setEstado("Enviado");
        envio.setPedido(pedido);
    }

    @Test
    public void testGetALLEnvios() throws Exception {
        when(envioService.obtenerTodos()).thenReturn(List.of(envio));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/envios"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(List.of(envio))));
    }

    @Test
    public void testGetEnvioById() throws Exception {
        when(envioService.ObtenerPorId(1L)).thenReturn(envio);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/envios/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(envio)));
    }

    @Test
    public void testCreateEnvio() throws Exception {
        when(envioService.guardar(envio)).thenReturn(envio);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/envios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(envio)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(envio)));
    }

    @Test
    public void testUpdateEnvio() throws Exception {
        when(envioService.guardar(envio)).thenReturn(envio);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/envios/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(envio)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(envio)));
    }

    @Test
    public void testDeleteEnvio() throws Exception {
        Long envioId = 1L;
        
        doNothing().when(envioService).eliminar(envioId);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/envios/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(envioService, new Times(1)).eliminar(envioId);
    }

}
