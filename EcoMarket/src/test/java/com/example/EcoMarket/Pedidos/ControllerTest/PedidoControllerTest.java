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

import Pedidos.controller.PedidoController;
import Pedidos.model.Cupon;
import Pedidos.model.DetallePedido;
import Pedidos.model.Pedido;
import Pedidos.service.PedidoService;
import Usuarios.model.Cliente;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;

    @BeforeEach
    void setup() {
        Cliente cliente = mock(Cliente.class);
        Cupon cupon = mock(Cupon.class);

        DetallePedido detallePedido = mock(DetallePedido.class);
        List<DetallePedido> detalleList = List.of(detallePedido);

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setFecha(java.time.LocalDateTime.now());
        pedido.setEstado("Pendiente");
        pedido.setTotal(100);
        pedido.setCliente(cliente);
        pedido.setDetalle(detalleList);
        pedido.setCupon(cupon);
    }

    @Test
    public void testGetALLPedidos() throws Exception {
        when(pedidoService.obtenerTodos()).thenReturn(List.of(pedido));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/pedidos"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(List.of(pedido))));
    }

    @Test
    public void testGetPedidoById() throws Exception {
        when(pedidoService.ObtenerPorId(1L)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/pedidos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(pedido)));
    }

    @Test
    public void testCreatePedido() throws Exception {
        when(pedidoService.guardar(pedido)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/pedidos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(pedido)));
    }

    @Test
    public void testUpdatePedido() throws Exception {
        when(pedidoService.guardar(pedido)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/pedidos/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(pedido)));
    }

    @Test
    public void testDeletePedido() throws Exception {
        Long pedidoId = 1L;
        
        doNothing().when(pedidoService).eliminar(pedidoId);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/pedidos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(pedidoService, new Times(1)).eliminar(pedidoId);
    }
}
