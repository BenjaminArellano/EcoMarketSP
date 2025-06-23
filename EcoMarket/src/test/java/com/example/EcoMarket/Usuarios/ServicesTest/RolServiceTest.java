package com.example.EcoMarket.Usuarios.ServicesTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Usuarios.model.Permiso;
import Usuarios.model.Rol;
import Usuarios.repository.RolRepository;
import Usuarios.service.RolService;

@SpringBootTest
public class RolServiceTest {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolRepository rolRepository;

    @Test
    public void testFindAll() {
        Permiso permiso = mock(Permiso.class);
        Set<Permiso> permisos = new HashSet<>();
        permisos.add(permiso);

        when(rolRepository.findAll()).thenReturn(List.of(new Rol(1L, permisos, "ADMIN")));
        List<Rol> roles = rolService.obtenerTodos();
        assertNotNull(roles);
        assertEquals(1, roles.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Permiso permiso = mock(Permiso.class);
        Set<Permiso> permisos = new HashSet<>();
        permisos.add(permiso);

        Rol rol = new Rol(id, permisos, "ADMIN");

        when(rolRepository.findById(id)).thenReturn(java.util.Optional.of(rol));
        Rol foundRol = rolService.ObtenerPorId(id);
        assertNotNull(foundRol);
        assertEquals("ADMIN", foundRol.getNombre());
    }

    @Test
    public void testSave() {
        Long id = 1L;
        Permiso permiso = mock(Permiso.class);
        Set<Permiso> permisos = new HashSet<>();
        permisos.add(permiso);

        Rol rol = new Rol(id, permisos, "ADMIN");

        when(rolRepository.save(rol)).thenReturn(rol);
        Rol savedRol = rolService.guardar(rol);
        assertNotNull(savedRol);
        assertEquals("ADMIN", savedRol.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        
        doNothing().when(rolRepository).deleteById(id);
        rolService.eliminar(id);

        verify(rolRepository, new Times(1)).deleteById(id);
    }

}
