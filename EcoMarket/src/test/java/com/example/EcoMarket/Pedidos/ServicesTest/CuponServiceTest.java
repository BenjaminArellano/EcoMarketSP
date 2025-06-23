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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import Pedidos.model.Cupon;
import Pedidos.model.Pedido;
import Pedidos.repository.CuponRepository;
import Pedidos.service.CuponService;


@SpringBootTest
public class CuponServiceTest {

    @Autowired
    private CuponService cuponService;

    @MockitoBean
    private CuponRepository cuponRepository;

    @Test
    public void testFindAll() {

        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);
        List<Pedido> pedidos = List.of(pedido1, pedido2);

        when(cuponRepository.findAll()).thenReturn(List.of(new Cupon(1L, "123", 50, pedidos)));

        List<Cupon> cupones = cuponService.obtenerTodos();

        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    @Test
    public void testFindById() {
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);
        List<Pedido> pedidos = List.of(pedido1, pedido2);

        Long id = 1L;
        Cupon cupon = new Cupon(id, "123", 50, pedidos);
        when(cuponRepository.findById(id)).thenReturn(java.util.Optional.of(cupon));

        Cupon foundCupon = cuponService.ObtenerPorId(id);

        assertNotNull(foundCupon);
        assertEquals(id, foundCupon.getId());
    }

    @Test
    public void testSave() {
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);
        List<Pedido> pedidos = List.of(pedido1, pedido2);

        Cupon cupon = new Cupon(1L, "123", 50, pedidos);
        when(cuponRepository.save(cupon)).thenReturn(cupon);

        Cupon savedCupon = cuponService.guardar(cupon);

        assertNotNull(savedCupon);
        assertEquals("123", savedCupon.getCodigo());
        assertEquals(50, savedCupon.getDescuento());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        doNothing().when(cuponRepository).deleteById(id);

        cuponService.eliminar(id);

        verify(cuponRepository, new Times(1)).deleteById(id);
    }

}
