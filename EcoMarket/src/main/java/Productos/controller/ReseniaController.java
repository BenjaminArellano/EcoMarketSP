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

import Productos.model.Resenia;
import Productos.service.ReseniaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/resenias")
@Tag(name="Resenia", description = "CRUD de las resenias")
public class ReseniaController {

    @Autowired
    private ReseniaService reseniaService;

    @GetMapping
    @Operation(summary = "Trae a todas las resenias de la BDD")
    public List<Resenia> obtenerTodos() {
        return reseniaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae a la resenia seleccionada")
    public Resenia obtenerPorId(@PathVariable Long id) {
        return reseniaService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Guarda la resenia")
    public Resenia guardar(@RequestBody Resenia resenia) {
        return reseniaService.guardar(resenia);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza la resenia selecionada")
    public Resenia actualizar(@PathVariable Long id, @RequestBody Resenia resenia) {
        resenia.setId(id);
        return reseniaService.guardar(resenia);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina la resenia")
    public String eliminar(@PathVariable Long id) {
        reseniaService.eliminar(id);
        return "Reseña eliminada con éxito";
    }
}