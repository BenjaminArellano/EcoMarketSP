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

import Pedidos.model.DetallePedido;
import Pedidos.service.DetallePedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/detallepedidos")
@Tag(name="DetallePedido", description = "CRUD de los detalles de los pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    @Operation(summary = "Trae a todos los detalles de la BDD")
    public List<DetallePedido> obtenerTodos() {
        return detallePedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al detalle pedido con el id")
    public DetallePedido obtenerPorId(@PathVariable Long id) {
        return detallePedidoService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "guarda el detalle")
    public DetallePedido guardar(@RequestBody DetallePedido detalle) {
        return detallePedidoService.guardar(detalle);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualiza el detalle pedido")
    public DetallePedido actualizar(@PathVariable Long id, @RequestBody DetallePedido detalle) {
        detalle.setId(id);
        return detallePedidoService.guardar(detalle);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina a el detalle pedido seleccionado")
    public String eliminar(@PathVariable Long id) {
        detallePedidoService.eliminar(id);
        return "Detalle de Pedido eliminado con éxito";
    }

}
