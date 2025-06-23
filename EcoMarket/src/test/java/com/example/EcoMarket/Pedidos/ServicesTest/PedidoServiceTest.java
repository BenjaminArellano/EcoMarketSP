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

import Pedidos.model.Cupon;
import Pedidos.model.DetallePedido;
import Pedidos.model.Pedido;
import Pedidos.repository.PedidoRepository;
import Pedidos.service.PedidoService;
import Usuarios.model.Cliente;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll() {
        
        Cliente cliente1 = mock(Cliente.class);
        Cupon cupon1 = mock(Cupon.class);

        DetallePedido detallePedido1 = mock(DetallePedido.class);
        DetallePedido detallePedido2 = mock(DetallePedido.class);
        List<DetallePedido> detalles = List.of(detallePedido1, detallePedido2);

        when(pedidoRepository.findAll()).thenReturn(List.of(new Pedido(1L, null, "Pendiente", 1000, cliente1, detalles, cupon1)));

        List<Pedido> pedidos = pedidoService.obtenerTodos();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindById() {
        Cliente cliente1 = mock(Cliente.class);
        Cupon cupon1 = mock(Cupon.class);

        DetallePedido detallePedido1 = mock(DetallePedido.class);
        DetallePedido detallePedido2 = mock(DetallePedido.class);
        List<DetallePedido> detalles = List.of(detallePedido1, detallePedido2);

        Long id = 1L;
        Pedido pedido = new Pedido(id, null, "Pendiente", 1000, cliente1, detalles, cupon1);

        when(pedidoRepository.findById(id)).thenReturn(java.util.Optional.of(pedido));

        Pedido foundPedido = pedidoService.ObtenerPorId(id);
        assertNotNull(foundPedido);
        assertEquals(id, foundPedido.getId());
    }

    @Test
    public void testSave() {
        Cliente cliente1 = mock(Cliente.class);
        Cupon cupon1 = mock(Cupon.class);

        DetallePedido detallePedido1 = mock(DetallePedido.class);
        DetallePedido detallePedido2 = mock(DetallePedido.class);
        List<DetallePedido> detalles = List.of(detallePedido1, detallePedido2);

        Pedido pedido = new Pedido(null, null, "Pendiente", 1000, cliente1, detalles, cupon1);

        when(pedidoRepository.save(pedido)).thenReturn(new Pedido(1L, null, "Pendiente", 1000, cliente1, detalles, cupon1));

        Pedido savedPedido = pedidoService.guardar(pedido);
        assertNotNull(savedPedido);
        assertEquals(1L, savedPedido.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(pedidoRepository).deleteById(id);
        pedidoService.eliminar(id);
        verify(pedidoRepository, new Times(1)).deleteById(id);

    }

}
