package Pedidos.controller;

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

import Pedidos.model.Cupon;
import Pedidos.service.CuponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cupones")
@Tag(name="Cupon", description = "CRUD de los cupones")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @GetMapping
    @Operation(summary = "Trae a todos los cupones de la BDD")
    public List<Cupon> obtenerTodos() {
        return cuponService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae el cupon con el id de la BDD")
    public Cupon obtenerPorId(@PathVariable Long id) {
        return cuponService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guarda un cupon")
    public Cupon guardar(@RequestBody Cupon cupon) {
        return cuponService.guardar(cupon);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un cupon")
    public Cupon actualizar(@PathVariable Long id, @RequestBody Cupon cupon) {
        cupon.setId(id);
        return cuponService.guardar(cupon);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina un cupon")
    public String eliminar(@PathVariable Long id) {
        cuponService.eliminar(id);
        return "cupon eliminado con éxito";
    }

}
