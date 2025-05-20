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


@RestController
@RequestMapping("/api/resenias")
public class ReseniaController {

    @Autowired
    private ReseniaService reseniaService;

    @GetMapping
    public List<Resenia> obtenerTodos() {
        return reseniaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Resenia obtenerPorId(@PathVariable Long id) {
        return reseniaService.obtenerPorId(id);
    }

    @PostMapping
    public Resenia guardar(@RequestBody Resenia resenia) {
        return reseniaService.guardar(resenia);
    }

    @PutMapping("/{id}")
    public Resenia actualizar(@PathVariable Long id, @RequestBody Resenia resenia) {
        resenia.setId(id);
        return reseniaService.guardar(resenia);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        reseniaService.eliminar(id);
        return "Reseña eliminada con éxito";
    }
}