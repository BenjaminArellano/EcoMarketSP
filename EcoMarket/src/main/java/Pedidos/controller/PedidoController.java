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

import Pedidos.model.Pedido;
import Pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name="Pedido", description = "CRUD de los pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Trae a todos los pedidos de la BDD")
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae a el pedido seleccionado")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "guarda el pedido")
    public Pedido guardar(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el pedido")
    public Pedido actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoService.guardar(pedido);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina el pedido")
    public String eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return "Pedido eliminado con éxito";
    }

}
