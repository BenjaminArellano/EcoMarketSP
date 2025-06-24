package com.example.EcoMarket.Productos.ControllerTest;

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

import Productos.controller.ProveedorController;
import Productos.model.Proveedor;
import Productos.service.ProveedorService;

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProveedorService proveedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Proveedor proveedor;

    @BeforeEach
    void setup() {
        proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Proveedor Test");
        proveedor.setContacto("Contacto Test");
        proveedor.setTelefono("123456789");
    }

    @Test
    public void testGetAllProveedores() throws Exception {
        when(proveedorService.obtenerTodos()).thenReturn(List.of(proveedor));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/proveedores"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("Proveedor Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].contacto").value("Contacto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].telefono").value("123456789"));
    }

    @Test
    public void testGetProveedorById() throws Exception {
        Long id = 1L;
        when(proveedorService.obtenerPorId(id)).thenReturn(proveedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/proveedores/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Proveedor Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.contacto").value("Contacto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("123456789"));
    }

    @Test
    public void testCreateProveedor() throws Exception {
        when(proveedorService.guardar(proveedor)).thenReturn(proveedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/proveedores")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Proveedor Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.contacto").value("Contacto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("123456789"));
    }

    @Test
    public void testUpdateProveedor() throws Exception {
        proveedor.setNombre("Proveedor Actualizado");
        when(proveedorService.guardar(proveedor)).thenReturn(proveedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/proveedores")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Proveedor Actualizado"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.contacto").value("Contacto Test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.telefono").value("123456789"));
    }

    @Test
    public void testDeleteProveedor() throws Exception {
        Long id = 1L;

        doNothing().when(proveedorService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/proveedores/" + id))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(proveedorService, new Times(1)).eliminar(id);
    }

}
