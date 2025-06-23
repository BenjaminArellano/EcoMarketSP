package com.example.EcoMarket.Usuarios.ServicesTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Usuarios.model.Empleado;
import Usuarios.model.Usuario;
import Usuarios.repository.EmpleadoRepository;
import Usuarios.service.EmpleadoService;

@SpringBootTest
public class EmpleadoServiceTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    public void testFindAll() {
        Usuario usuario = mock(Usuario.class);

        when(empleadoRepository.findAll()).thenReturn(List.of(new Empleado(1L, usuario, "Juan Perez", "Gerente", "juan.perez@gmail.com")));

        List<Empleado> empleados = empleadoService.obtenerTodos();
        assertNotNull(empleados);
        assertEquals(1, empleados.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = mock(Usuario.class);
        Empleado empleado = new Empleado(id, usuario, "Juan Perez", "Gerente", "juan.perez@gmail.com");

        when(empleadoRepository.findById(id)).thenReturn(java.util.Optional.of(empleado));
        Empleado foundEmpleado = empleadoService.ObtenerPorId(id);
        assertNotNull(foundEmpleado);
        assertEquals(id, foundEmpleado.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = mock(Usuario.class);
        Empleado empleado = new Empleado(1L, usuario, "Juan Perez", "Gerente", "juan.perez@gmail.com");

        when(empleadoRepository.save(empleado)).thenReturn(empleado);  
        Empleado savedEmpleado = empleadoService.guardar(empleado);
        assertNotNull(savedEmpleado);
        assertEquals(savedEmpleado.getId(), savedEmpleado.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        empleadoService.eliminar(id);

        verify(empleadoRepository, new Times(1)).deleteById(id);
    }
}
