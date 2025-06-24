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

import Productos.model.Proveedor;
import Productos.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/proveedores")
@Tag(name="Proveedor", description = "CRUD de los proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Trae a todos los proveedores de la BDD")
    public List<Proveedor> obtenerTodos() {
        return proveedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al proveedor seleccionado")
    public Proveedor obtenerPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guarda al proveedor")
    public Proveedor guardar(@RequestBody Proveedor proveedor) {
        return proveedorService.guardar(proveedor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza al proveedor selecionado")
    public Proveedor actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorService.guardar(proveedor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina al proveedor")
    public String eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return "Proveedor eliminado con éxito";
    }
}