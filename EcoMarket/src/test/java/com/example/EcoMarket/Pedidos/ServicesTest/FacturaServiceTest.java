package com.example.EcoMarket.Pedidos.ServicesTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Pedidos.model.Factura;
import Pedidos.model.Pedido;
import Pedidos.repository.FacturaRepository;
import Pedidos.service.FacturaService;

@SpringBootTest
public class FacturaServiceTest {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    public void testFindAll() {
        Pedido pedido1 = mock(Pedido.class);

        when(facturaRepository.findAll()).thenReturn(List.of(new Factura(1L, LocalDateTime.now(), 1000, pedido1)));

        List<Factura> facturas = facturaService.obtenerTodos();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
    }

    @Test
    public void testFindById() {
        Pedido pedido1 = mock(Pedido.class);
        Long id = 1L;
        Factura factura = new Factura(id, LocalDateTime.now(), 1000, pedido1);

        when(facturaRepository.findById(id)).thenReturn(java.util.Optional.of(factura));

        Factura foundFactura = facturaService.ObtenerPorId(id);

        assertNotNull(foundFactura);
        assertEquals(id, foundFactura.getId());
    }

    @Test
    public void testSave() {
        Pedido pedido1 = mock(Pedido.class);
        Factura factura = new Factura(null, LocalDateTime.now(), 1000, pedido1);

        when(facturaRepository.save(factura)).thenReturn(new Factura(1L, LocalDateTime.now(), 1000, pedido1));

        Factura savedFactura = facturaService.guardar(factura);

        assertNotNull(savedFactura);
        assertEquals(1L, savedFactura.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;


        facturaService.eliminar(id);


        assertNotNull(facturaRepository.findById(id).orElse(null));
    }

}
