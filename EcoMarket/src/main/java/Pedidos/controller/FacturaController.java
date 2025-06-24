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

import Pedidos.model.Factura;
import Pedidos.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/facturas")
@Tag(name="Facturas", description = "CRUD de las Facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    @Operation(summary = "Trae a todas las facturas de la BDD")
    public List<Factura> obtenerTodos() {
        return facturaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae a la factura selecionada")
    public Factura obtenerPorId(@PathVariable Long id) {
        return facturaService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "guarda a la factura")
    public Factura guardar(@RequestBody Factura factura) {
        return facturaService.guardar(factura);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualiza la factura")
    public Factura actualizar(@PathVariable Long id, @RequestBody Factura factura) {
        factura.setId(id);
        return facturaService.guardar(factura);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina la factura selecionada")
    public String eliminar(@PathVariable Long id) {
        facturaService.eliminar(id);
        return "Factura eliminado con éxito";
    }

}
