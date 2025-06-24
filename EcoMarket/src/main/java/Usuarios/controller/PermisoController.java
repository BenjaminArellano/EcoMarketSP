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

import Usuarios.model.Permiso;
import Usuarios.service.PermisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/permisos")
@Tag(name="Permiso", description = "CRUD de los permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    @Operation(summary = "Trae todos los permisos de la BDD")
    public List<Permiso> obtenerTodos() {
        return permisoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al permiso seleccionado")
    public Permiso obtenerPorId(@PathVariable Long id) {
        return permisoService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guardar el permiso")
    public Permiso guardar(@RequestBody Permiso permiso) {
        return permisoService.guardar(permiso);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el permiso seleccionado")
    public Permiso actualizar(@PathVariable Long id, @RequestBody Permiso permiso) {
        permiso.setId(id);
        return permisoService.guardar(permiso);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina el permiso seleccionado")
    public String eliminar(@PathVariable Long id) {
        permisoService.eliminar(id);
        return "Permiso eliminado con éxito";
    }
}
