package com.example.EcoMarket.Pedidos.ServicesTest;

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

import Pedidos.model.DetallePedido;
import Pedidos.model.Pedido;
import Pedidos.repository.DetallePedidoRepository;
import Pedidos.service.DetallePedidoService;
import Productos.model.Producto;

@SpringBootTest
public class DetallePedidoServiceTest {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Test
    public void testFindAll() {
        
        Pedido pedido1 = mock(Pedido.class);
        Producto producto1 = mock(Producto.class);

        when(detallePedidoRepository.findAll()).thenReturn(List.of(new DetallePedido(1L, 2, 500, pedido1, producto1)));

        List<DetallePedido> detalles = detallePedidoService.obtenerTodos();

        assertNotNull(detalles);
        assertEquals(1, detalles.size());
    }

    @Test
    public void testFindById() {
        Pedido pedido1 = mock(Pedido.class);
        Producto producto1 = mock(Producto.class);
        Long id = 1L;
        DetallePedido detallePedido = new DetallePedido(id, 2, 500, pedido1, producto1);

        when(detallePedidoRepository.findById(id)).thenReturn(java.util.Optional.of(detallePedido));

        DetallePedido foundDetalle = detallePedidoService.ObtenerPorId(id);

        assertNotNull(foundDetalle);
        assertEquals(id, foundDetalle.getId());
    }

    @Test
    public void testSave() {
        Pedido pedido1 = mock(Pedido.class);
        Producto producto1 = mock(Producto.class);
        DetallePedido detallePedido = new DetallePedido(1L, 2, 500, pedido1, producto1);

        when(detallePedidoRepository.save(detallePedido)).thenReturn(detallePedido);

        DetallePedido savedDetalle = detallePedidoService.guardar(detallePedido);

        assertNotNull(savedDetalle);
        assertEquals(detallePedido.getId(), savedDetalle.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(detallePedidoRepository).deleteById(id);
        detallePedidoService.eliminar(id);
        verify(detallePedidoRepository, new Times(1)).deleteById(id);
    }

}
