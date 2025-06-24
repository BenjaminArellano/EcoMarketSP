package com.example.EcoMarket.Usuarios.ControllerTest;

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

import Usuarios.controller.EmpleadoController;
import Usuarios.model.Empleado;
import Usuarios.model.Usuario;
import Usuarios.service.EmpleadoService;

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Empleado empleado;

    @BeforeEach
    void setup() {
        Usuario usuario = mock(Usuario.class);

        empleado = new Empleado();
        empleado.setId(1L);
        empleado.setUsuario(usuario);
        empleado.setNombre("Ana Gomez");
        empleado.setCargo("Gerente");
        empleado.setCorreo("ana.gomez@gmail.com");
    }

    @Test
    void testGetAllEmpleados() throws Exception {
        when(empleadoService.obtenerTodos()).thenReturn(List.of(empleado));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/empleados"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("Ana Gomez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].cargo").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].correo").value("ana.gomez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].usuario").isNotEmpty());
    }

    @Test
    void testGetEmpleadoById() throws Exception {
        when(empleadoService.ObtenerPorId(1L)).thenReturn(empleado);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/empleados/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Ana Gomez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cargo").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("ana.gomez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.usuario").isNotEmpty());
    }

    @Test
    void testCreateEmpleado() throws Exception {
        when(empleadoService.guardar(empleado)).thenReturn(empleado);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/empleados")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Ana Gomez"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cargo").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("ana.gomez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.usuario").isNotEmpty());
    }

    @Test
    void testUpdateEmpleado() throws Exception {
        empleado.setNombre("Ana Gomez Actualizada");
        when(empleadoService.guardar(empleado)).thenReturn(empleado);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/empleados/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Ana Gomez Actualizada"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.cargo").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.correo").value("ana.gomez@gmail.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.usuario").isNotEmpty());
    }

    @Test
    void testDeleteEmpleado() throws Exception {
        Long id = 1L;
        
        doNothing().when(empleadoService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/empleados/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(empleadoService, new Times(1)).eliminar(id);
    }

}
