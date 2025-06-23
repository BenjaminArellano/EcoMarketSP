package com.example.EcoMarket.Pedidos.ServicesTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Pedidos.model.Envio;
import Pedidos.model.Pedido;
import Pedidos.repository.EnvioRepository;
import Pedidos.service.EnvioService;

@SpringBootTest
public class EnvioServiceTest {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioRepository envioRepository;

    @Test
    public void testFindAll() {
        
        Pedido pedido1 = mock(Pedido.class);

        when(envioRepository.findAll()).thenReturn(List.of(new Envio(1L, "Calle Falsa 123", "Enviado", pedido1)));

        List<Envio> envios = envioService.obtenerTodos();
        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testFindById() {
        Pedido pedido1 = mock(Pedido.class);
        Long id = 1L;
        Envio envio = new Envio(id, "Calle Falsa 123", "Enviado", pedido1);

        when(envioRepository.findById(id)).thenReturn(java.util.Optional.of(envio));

        Envio foundEnvio = envioService.ObtenerPorId(id);

        assertNotNull(foundEnvio);
        assertEquals(id, foundEnvio.getId());
    }

    @Test
    public void testSave() {
        Pedido pedido1 = mock(Pedido.class);
        Envio envio = new Envio(null, "Calle Falsa 123", "Enviado", pedido1);

        when(envioRepository.save(envio)).thenReturn(new Envio(1L, "Calle Falsa 123", "Enviado", pedido1));

        Envio savedEnvio = envioService.guardar(envio);

        assertNotNull(savedEnvio);
        assertEquals("Calle Falsa 123", savedEnvio.getDireccion());
    }

    @Test
    public void testDelete() {
        Long id = 1L;


        when(envioRepository.existsById(id)).thenReturn(false);

        envioService.eliminar(id);


        assertEquals(false, envioRepository.existsById(id));
    }

}
