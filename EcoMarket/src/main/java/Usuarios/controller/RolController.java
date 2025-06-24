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

import Usuarios.model.Rol;
import Usuarios.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/roles")
@Tag(name="Rol", description = "CRUD de los permisos")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "Trae todos los roles de la BDD")
    public List<Rol> obtenerTodos() {
        return rolService.obtenerTodos();
    }

    @PostMapping
    @Operation(summary = "Guarda el rol")
    public Rol guardar(@RequestBody Rol rol) {
        return rolService.guardar(rol);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae el rol seleccionado")
    public Rol obtenerPorId(@PathVariable Long id) {
        return rolService.ObtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el rol seleccionado")
    public Rol actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        rol.setId(id);
        return rolService.guardar(rol);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina el rol seleccionado")
    public String eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return "rol eliminado con éxito";
    }

}
