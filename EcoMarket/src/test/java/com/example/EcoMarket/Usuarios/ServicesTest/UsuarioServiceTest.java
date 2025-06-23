package com.example.EcoMarket.Usuarios.ServicesTest;

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

import Usuarios.model.Rol;
import Usuarios.model.Usuario;
import Usuarios.repository.UsuarioRepository;
import Usuarios.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Rol rol = mock(Rol.class);

        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1L, "John", "Doe", "jhon.doe@gmail.com", "password123", rol)));
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Rol rol = mock(Rol.class);

        Usuario usuario = new Usuario(id, "John", "Doe", "jhon.doe@gmail.com", "password123", rol);

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        Usuario foundUsuario = usuarioService.ObtenerPorId(id);
        assertNotNull(foundUsuario);
        assertEquals("John", foundUsuario.getNombre());

    }

    @Test
    public void testSave() {
        Rol rol = mock(Rol.class);
        Usuario usuario = new Usuario(1L, "John", "Doe", "jhon.doe@gmail.com", "password123", rol);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario savedUsuario = usuarioService.guardar(usuario);
        assertNotNull(savedUsuario);
        assertEquals("John", savedUsuario.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(usuarioRepository).deleteById(id);
        usuarioService.eliminar(id);
        verify(usuarioRepository, new Times(1)).deleteById(id);
    }

}
