package com.example.EcoMarket.Productos.ControllerTest;

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

import Productos.controller.InventarioController;
import Productos.model.Inventario;
import Productos.model.Producto;
import Productos.service.InventarioService;


@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventario;

    @BeforeEach
    void setup() {
        Producto producto = mock(Producto.class);

        inventario = new Inventario();
        inventario.setId(1L);
        inventario.setStock(100);
        inventario.setProducto(producto);
    }

    @Test
    public void testGetAllInventarios() throws Exception {
        
        when(inventarioService.obtenerTodos()).thenReturn(List.of(inventario));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/inventarios"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].stock").value(100));
    }

    @Test
    public void testGetInventarioById() throws Exception {
        when(inventarioService.obtenerPorId(1L)).thenReturn(inventario);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/inventarios/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.stock").value(100));
    }

    @Test
    public void testCreateInventario() throws Exception {
        when(inventarioService.guardar(inventario)).thenReturn(inventario);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/inventarios")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.stock").value(100));
    }

    @Test
    public void testUpdateInventario() throws Exception {
        inventario.setStock(150);
        when(inventarioService.guardar(inventario)).thenReturn(inventario);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/inventarios/1")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.stock").value(150));
    }

    @Test
    public void testDeleteInventario() throws Exception {
        Long id = 1L;

        doNothing().when(inventarioService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/inventarios/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(inventarioService, new Times(1)).eliminar(id);
    }

}
