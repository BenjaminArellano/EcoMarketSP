package Productos.controller;

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

import Productos.model.Inventario;
import Productos.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/inventarios")
@Tag(name="Inventario", description = "CRUD de los inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Trae a todos los inventarios de la BDD")
    public List<Inventario> obtenerTodos() {
        return inventarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae el inventario selecionado")
    public Inventario obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guarda el inventario")
    public Inventario guardar(@RequestBody Inventario inventario) {
        return inventarioService.guardar(inventario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el inventario")
    public Inventario actualizar(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return inventarioService.guardar(inventario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina el inventario")
    public String eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return "Inventario eliminado con éxito";
    }
}