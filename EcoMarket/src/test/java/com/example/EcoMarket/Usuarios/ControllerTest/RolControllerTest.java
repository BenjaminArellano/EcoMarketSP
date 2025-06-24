package com.example.EcoMarket.Usuarios.ControllerTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import Usuarios.controller.RolController;
import Usuarios.model.Permiso;
import Usuarios.model.Rol;
import Usuarios.service.RolService;

@WebMvcTest(RolController.class)
public class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RolService rolService;

    @Autowired
    private ObjectMapper objectMapper;

    private Rol rol;

    @BeforeEach
    void setup() {
        Permiso permiso = mock(Permiso.class);
        Set<Permiso> permisos = new HashSet<>();
        permisos.add(permiso);

        rol = new Rol();
        rol.setId(1L);
        rol.setPermisos(permisos);
        rol.setNombre("Gerente");
    }

    @Test
    void testGetAllRoles() throws Exception {
        when(rolService.obtenerTodos()).thenReturn(List.of(rol));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/roles"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].permisos").isNotEmpty());
    }

    @Test
    void testGetRolById() throws Exception {
        when(rolService.ObtenerPorId(1L)).thenReturn(rol);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/roles/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.permisos").isNotEmpty());
    }

    @Test
    void testCreateRol() throws Exception {
        when(rolService.guardar(rol)).thenReturn(rol);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/roles")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rol)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Gerente"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.permisos").isNotEmpty());
    }

    @Test
    void testUpdateRol() throws Exception {
        rol.setNombre("Gerente Actualizado");
        when(rolService.guardar(rol)).thenReturn(rol);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/roles/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rol)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.nombre").value("Gerente Actualizado"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.permisos").isNotEmpty());
    }

    @Test
    void testDeleteRol() throws Exception {
        Long id = 1L;
        doNothing().when(rolService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/roles/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(rolService, new Times(1)).eliminar(id);
    }
}
