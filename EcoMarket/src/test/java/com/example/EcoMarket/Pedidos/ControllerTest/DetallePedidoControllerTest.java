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

import Pedidos.controller.DetallePedidoController;
import Pedidos.model.DetallePedido;
import Pedidos.model.Pedido;
import Pedidos.service.DetallePedidoService;
import Productos.model.Producto;

@WebMvcTest(DetallePedidoController.class)
public class DetallePedidoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DetallePedidoService detallePedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private DetallePedido detallePedido;

    @BeforeEach
    void setup() {

        Pedido pedido = mock(Pedido.class);
        Producto producto = mock(Producto.class);

        detallePedido = new DetallePedido();
        detallePedido.setId(1L);
        detallePedido.setCantidad(2);
        detallePedido.setPrecio(100);
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
    }

    @Test
    public void testGetAllDetallesPedidos() throws Exception {
        
        when(detallePedidoService.obtenerTodos()).thenReturn(List.of(detallePedido));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/detalle-pedidos"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].cantidad").value(2))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].precio").value(100));
    }

    @Test
    public void testGetDetallePedidoById() throws Exception {
        when(detallePedidoService.ObtenerPorId(1L)).thenReturn(detallePedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/detalle-pedidos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cantidad").value(2))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100));
    }

    @Test
    public void testCreateDetallePedido() throws Exception {
        when(detallePedidoService.guardar(detallePedido)).thenReturn(detallePedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/detalle-pedidos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(detallePedido)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cantidad").value(2))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100));
    }

    @Test
    public void testUpdateDetallePedido() throws Exception {
        when(detallePedidoService.guardar(detallePedido)).thenReturn(detallePedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/detalle-pedidos/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(detallePedido)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cantidad").value(2))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.precio").value(100));
    }

    @Test
    public void testDeleteDetallePedido() throws Exception {
        Long id = 1L;

        doNothing().when(detallePedidoService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/detalle-pedidos/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(detallePedidoService, new Times(1)).eliminar(id);
    }

}
