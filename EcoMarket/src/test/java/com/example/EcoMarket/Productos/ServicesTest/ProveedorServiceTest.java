package com.example.EcoMarket.Productos.ServicesTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Productos.model.Proveedor;
import Productos.repository.ProveedorRepository;
import Productos.service.ProveedorService;

@SpringBootTest
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    public void testFindAll() {
        
        when(proveedorRepository.findAll()).thenReturn(List.of(new Proveedor(1L, "Proveedor1", "Contacto1", "1234567890")));
        List<Proveedor> proveedores = proveedorService.obtenerTodos();
        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Proveedor proveedor = new Proveedor(id, "Proveedor1", "Contacto1", "1234567890");

        when(proveedorRepository.findById(id)).thenReturn(java.util.Optional.of(proveedor));

        Proveedor foundProveedor = proveedorService.obtenerPorId(id);
        assertNotNull(foundProveedor);
        assertEquals(id, foundProveedor.getId());
    }

    @Test
    public void testSave() {
        Proveedor proveedor = new Proveedor(1L, "Proveedor1", "Contacto1", "1234567890");

        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor savedProveedor = proveedorService.guardar(proveedor);
        assertNotNull(savedProveedor);
        assertEquals("Proveedor1", savedProveedor.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(proveedorRepository).deleteById(id);
        proveedorService.eliminar(id);
        
        verify(proveedorRepository, new Times(1)).deleteById(id);
    }

}
