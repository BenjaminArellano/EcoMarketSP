package Usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Usuarios.model.Usuario;
import Usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name="Usuario", description = "CRUD de los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Trae todos los usuarios de la BDD")
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping
    @Operation(summary = "Guarda el usuario")
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al usuario seleccionado")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.ObtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el usuario seleccionado")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.ObtenerPorId(id);
        if (usuarioExistente != null) {
            usuario.setId(id);
            return usuarioService.guardar(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina el usuario seleccionado")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "Usuario eliminado con éxito";
    }



}
