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

import Productos.model.Producto;
import Productos.model.Proveedor;
import Productos.repository.ProductoRepository;
import Productos.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        Proveedor proveedor1 = mock(Proveedor.class);

        when(productoRepository.findAll()).thenReturn(List.of(
            new Producto(1L, "Producto 1", "Descripcion 1", "Categoria 1", 100.0, proveedor1),
            new Producto(2L, "Producto 2", "Descripcion 2", "Categoria 2", 200.0, proveedor1)
        ));

        List<Producto> productos = productoService.obtenerTodos();

        assertNotNull(productos);
        assertEquals(2, productos.size());
    }

    @Test
    public void testFindById() {
        Proveedor proveedor1 = mock(Proveedor.class);
        Long id = 1L;
        Producto producto = new Producto(id, "Producto 1", "Descripcion 1", "Categoria 1", 100.0, proveedor1);

        when(productoRepository.findById(id)).thenReturn(java.util.Optional.of(producto));

        Producto foundProducto = productoService.obtenerPorId(id);

        assertNotNull(foundProducto);
        assertEquals(id, foundProducto.getId());
    }

    @Test
    public void testSave() {
        Proveedor proveedor1 = mock(Proveedor.class);
        Producto producto = new Producto(1L, "Producto 1", "Descripcion 1", "Categoria 1", 100.0, proveedor1);

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto savedProducto = productoService.guardar(producto);

        assertNotNull(savedProducto);
        assertEquals("Producto 1", savedProducto.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(productoRepository).deleteById(id);
        productoService.eliminar(id);

        verify(productoRepository, new Times(1)).deleteById(id);

        
    }

}
