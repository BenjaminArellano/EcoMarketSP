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

import Usuarios.model.Cliente;
import Usuarios.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name="Cliente", description = "CRUD de los clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Trae a todos los clientes de la BDD")
    public List<Cliente> obtenerTodos() {
        return clienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al cliente seleccionado")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guarda al cliente")
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza al cliente seleccionado")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.guardar(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina al cliente seleccinado")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "Cliente eliminado con éxito";
    }

}
