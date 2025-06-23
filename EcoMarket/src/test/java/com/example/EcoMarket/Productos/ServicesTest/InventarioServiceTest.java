package com.example.EcoMarket.Productos.ServicesTest;

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

import Productos.model.Inventario;
import Productos.model.Producto;
import Productos.repository.InventarioRepository;
import Productos.service.InventarioService;

@SpringBootTest
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Test
    public void testFindAll() {
        
        Producto producto1 = mock(Producto.class);

        when(inventarioRepository.findAll()).thenReturn(List.of(new Inventario(1L, 100, producto1)));

        List<Inventario> inventarios = inventarioService.obtenerTodos();
        assertNotNull(inventarios);
        assertEquals(1, inventarios.size());
    }

    @Test
    public void testFindById() {
        Producto producto1 = mock(Producto.class);
        Long id = 1L;
        Inventario inventario = new Inventario(id, 100, producto1);

        when(inventarioRepository.findById(id)).thenReturn(java.util.Optional.of(inventario));

        Inventario foundInventario = inventarioService.obtenerPorId(id);
        assertNotNull(foundInventario);
        assertEquals(id, foundInventario.getId());
    }

    @Test
    public void testSave() {
        Producto producto1 = mock(Producto.class);
        Inventario inventario = new Inventario(1L, 100, producto1);

        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        Inventario savedInventario = inventarioService.guardar(inventario);
        assertNotNull(savedInventario);
        assertEquals(inventario.getId(), savedInventario.getId());
    }

    @Test
    public void testDelete() {

        Long id = 1L;

        doNothing().when(inventarioRepository).deleteById(id);

        inventarioService.eliminar(id);

        verify(inventarioRepository, new Times(1)).deleteById(id);
    }



}
