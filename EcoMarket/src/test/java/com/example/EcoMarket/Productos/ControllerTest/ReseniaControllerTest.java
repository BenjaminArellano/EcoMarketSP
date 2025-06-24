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

import Productos.controller.ReseniaController;
import Productos.model.Producto;
import Productos.model.Resenia;
import Productos.service.ReseniaService;
import Usuarios.model.Cliente;

@WebMvcTest(ReseniaController.class)
public class ReseniaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReseniaService reseniaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Resenia resenia;

    @BeforeEach
    void setup() {
        Producto producto = mock(Producto.class);
        Cliente cliente = mock(Cliente.class);

        resenia = new Resenia();
        resenia.setId(1L);
        resenia.setCalificacion(5);
        resenia.setComentario("Excelente producto");
        resenia.setProducto(producto);
        resenia.setCliente(cliente);
    }

    @Test
    public void testGetAllResenias() throws Exception {
        when(reseniaService.obtenerTodos()).thenReturn(List.of(resenia));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/resenias"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].calificacion").value(5))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].comentario").value("Excelente producto"));
    }

    @Test
    public void testGetReseniaById() throws Exception {
        Long id = 1L;
        when(reseniaService.obtenerPorId(id)).thenReturn(resenia);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/resenias/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.calificacion").value(5))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.comentario").value("Excelente producto"));
    }

    @Test
    public void testCreateResenia() throws Exception {
        when(reseniaService.guardar(resenia)).thenReturn(resenia);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/resenias")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(resenia)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.calificacion").value(5))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.comentario").value("Excelente producto"));
    }

    @Test
    public void testUpdateResenia() throws Exception {
        resenia.setCalificacion(4);
        resenia.setComentario("Buen producto");
        when(reseniaService.guardar(resenia)).thenReturn(resenia);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/resenias")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(resenia)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.calificacion").value(4))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.comentario").value("Buen producto"));
    }

    @Test
    public void testDeleteResenia() throws Exception {
        Long id = 1L;
        
        doNothing().when(reseniaService).eliminar(id);
        

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/resenias/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(reseniaService, new Times(1)).eliminar(id);
    }





}
