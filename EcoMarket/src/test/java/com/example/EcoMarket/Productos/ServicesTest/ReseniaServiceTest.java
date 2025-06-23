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
import Productos.model.Resenia;
import Productos.repository.ReseniaRepository;
import Productos.service.ReseniaService;
import Usuarios.model.Cliente;

@SpringBootTest
public class ReseniaServiceTest {

    @Autowired
    private ReseniaService reseniaService;

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Test
    public void testFindAll() {
        Producto producto = mock(Producto.class);
        Cliente cliente = mock(Cliente.class);
        
        when(reseniaRepository.findAll()).thenReturn(List.of(
            new Resenia(1L, 5, "Excelente producto", producto, cliente),
            new Resenia(2L, 4, "Muy bueno", producto, cliente)
        ));

        List<Resenia> resenias = reseniaService.obtenerTodos();
        assertNotNull(resenias);
        assertEquals(2, resenias.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Producto producto = mock(Producto.class);
        Cliente cliente = mock(Cliente.class);
        Resenia resenia = new Resenia(id, 5, "Excelente producto", producto, cliente);

        when(reseniaRepository.findById(id)).thenReturn(java.util.Optional.of(resenia));

        Resenia foundResenia = reseniaService.obtenerPorId(id);
        assertNotNull(foundResenia);
        assertEquals(id, foundResenia.getId());
    }

    @Test
    public void testSave() {
        Producto producto = mock(Producto.class);
        Cliente cliente = mock(Cliente.class);
        Resenia resenia = new Resenia(1L, 5, "Excelente producto", producto, cliente);

        when(reseniaRepository.save(resenia)).thenReturn(resenia);

        Resenia savedResenia = reseniaService.guardar(resenia);
        assertNotNull(savedResenia);
        assertEquals("Excelente producto", savedResenia.getComentario());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(reseniaRepository).deleteById(id);
        reseniaService.eliminar(id);

        verify(reseniaRepository, new Times(1)).deleteById(id);
        
        
    }

}
