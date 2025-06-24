package com.example.EcoMarket.Usuarios.ServicesTest;

import java.util.List;

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
import Usuarios.repository.PermisoRepository;
import Usuarios.service.PermisoService;

@SpringBootTest
public class PermisoServiceTest {

    @Autowired
    private PermisoService permisoService;

    @Autowired
    private PermisoRepository permisoRepository;

    @Test
    public void testFindAll() {

        Rol rol = mock(Rol.class);
        List<Rol> roles = List.of(rol);
        
        when(permisoRepository.findAll()).thenReturn(List.of(
            new Permiso(1L, "READ_PRIVILEGES", "Permite leer datos", roles),
            new Permiso(2L, "WRITE_PRIVILEGES", "Permite escribir datos", roles)
        ));

        List<Permiso> permisos = permisoService.obtenerTodos();
        assertNotNull(permisos);
        assertEquals(2, permisos.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Rol rol = mock(Rol.class);
        List<Rol> roles = List.of(rol);
        roles.add(rol);
        
        Permiso permiso = new Permiso(id, "READ_PRIVILEGES", "Permite leer datos", roles);

        when(permisoRepository.findById(id)).thenReturn(java.util.Optional.of(permiso));
        Permiso foundPermiso = permisoService.ObtenerPorId(id);
        assertNotNull(foundPermiso);
        assertEquals(id, foundPermiso.getId());
    }

    @Test
    public void testSave() {
        Rol rol = mock(Rol.class);
        List<Rol> roles = List.of(rol);
        roles.add(rol);
        
        Permiso permiso = new Permiso(1L, "READ_PRIVILEGES", "Permite leer datos", roles);

        when(permisoRepository.save(permiso)).thenReturn(permiso);  
        Permiso savedPermiso = permisoService.guardar(permiso);
        assertNotNull(savedPermiso);
        assertEquals(savedPermiso.getId(), savedPermiso.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(permisoRepository).deleteById(id);
        permisoService.eliminar(id);

        verify(permisoRepository, new Times(1)).deleteById(id);
        
    }

}
