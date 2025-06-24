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

import Pedidos.model.Envio;
import Pedidos.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/envios")
@Tag(name="Envio", description = "CRUD de los detalles de los envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Trae a todos los Envios de la BDD")
    public List<Envio> obtenerTodos() {
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al envio seleccionado por id")
    public Envio obtenerPorId(@PathVariable Long id) {
        return envioService.ObtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "guarda el envio")
    public Envio guardar(@RequestBody Envio envio) {
        return envioService.guardar(envio);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualiza el envio")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        return envioService.guardar(envio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina el envio")
    public String eliminar(@PathVariable Long id) {
        envioService.eliminar(id);
        return "Envio eliminado con éxito";
    }

}
