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

import Usuarios.controller.PermisoController;
import Usuarios.model.Permiso;
import Usuarios.model.Rol;
import Usuarios.service.PermisoService;

@WebMvcTest(PermisoController.class)
public class PermisoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PermisoService permisoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Permiso permiso;

    @BeforeEach
    void setup() {
        Rol rol = mock(Rol.class);
        List<Rol> roles = List.of(rol);

        permiso = new Permiso();
        permiso.setId(1L);
        permiso.setNombre("ADMIN");
        permiso.setDescripcion("Permiso de administrador");
        permiso.setRoles(roles);
    }

    @Test
    void testGetAllPermisos() throws Exception {
        when(permisoService.obtenerTodos()).thenReturn(java.util.List.of(permiso));
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/permisos"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("ADMIN"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Permiso de administrador"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].roles").isNotEmpty());
    }

    @Test
    void testGetPermisoById() throws Exception {

        when(permisoService.ObtenerPorId(1L)).thenReturn(permiso);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/permisos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("ADMIN"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Permiso de administrador"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].roles").isNotEmpty());
    
    }

    @Test
    void testCreatePermiso() throws Exception {
        when(permisoService.guardar(permiso)).thenReturn(permiso);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/permisos")
                .contentType("application/json").content(objectMapper.writeValueAsString(permiso)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("ADMIN"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Permiso de administrador"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].roles").isNotEmpty());
    
    }

    @Test
    void testUpdatePermiso() throws Exception {
        permiso.setNombre("ADMIN Actualizado");
        when(permisoService.guardar(permiso)).thenReturn(permiso);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/permisos/1")
                .contentType("application/json").content(objectMapper.writeValueAsString(permiso)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nombre").value("ADMIN"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Permiso de administrador"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].roles").isNotEmpty());
    }

    @Test
    void testDeleteEmpleado() throws Exception {
        Long id = 1L;
        
        doNothing().when(permisoService).eliminar(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/permisos/1"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNoContent());

        verify(permisoService, new Times(1)).eliminar(id);
    }

}
