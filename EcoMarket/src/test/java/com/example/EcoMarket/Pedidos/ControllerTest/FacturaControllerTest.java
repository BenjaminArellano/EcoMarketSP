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

import Pedidos.controller.FacturaController;
import Pedidos.model.Factura;
import Pedidos.model.Pedido;
import Pedidos.service.FacturaService;

@WebMvcTest(FacturaController.class)
public class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacturaService facturaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Factura factura;

    @BeforeEach
    void setup() {

        Pedido pedido = mock(Pedido.class);
        factura = new Factura();
        factura.setId(1L);
        factura.setFecha(java.time.LocalDateTime.now());
        factura.setTotal(100);
        factura.setPedido(pedido);
    }  

    @Test
    public void testGetALLFacturas() throws Exception {
        when(facturaService.obtenerTodos()).thenReturn(List.of(factura));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/facturas"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(List.of(factura))));
    }

    @Test
    public void testGetFacturaById() throws Exception {
        when(facturaService.ObtenerPorId(1L)).thenReturn(factura);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/facturas/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(factura)));
    }

    @Test
    public void testCreateFactura() throws Exception {
        when(facturaService.guardar(factura)).thenReturn(factura);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/facturas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(factura)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(factura)));
    }

    @Test
    public void testUpdateFactura() throws Exception {
        when(facturaService.guardar(factura)).thenReturn(factura);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/facturas/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(factura)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(factura)));
    }

    @Test
    public void testDeleteFactura() throws Exception {
        Long facturaId = 1L;
        
        doNothing().when(facturaService).eliminar(facturaId);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/facturas/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(facturaService, new Times(1)).eliminar(facturaId);
    }

}
