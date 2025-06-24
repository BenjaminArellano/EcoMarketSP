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

import Usuarios.model.Empleado;
import Usuarios.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/empleados")
@Tag(name="Empleado", description = "CRUD de los empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    
    @GetMapping
    @Operation(summary = "Trae a todos los empleados de la BDD")
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @PostMapping
    @Operation(summary = "Guarda al empleado")
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trae al empleado seleccionado")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.ObtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el empleado seleccionado")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return empleadoService.guardar(empleado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina al empleado seleccionado")
    public String eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return "Empleado eliminado con éxito";
    }
}
