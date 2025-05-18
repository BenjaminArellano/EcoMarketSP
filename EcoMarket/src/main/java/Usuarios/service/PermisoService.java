package Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Usuarios.model.Permiso;
import Usuarios.repository.PermisoRepository;


@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    public List<Permiso> obtenerTodos() {
        return permisoRepository.findAll();
    }

    public Permiso ObtenerPorId(Long id) {
        return permisoRepository.findById(id).orElse(null);
    }

    public Permiso guardar(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    public void eliminar(Long id) {
        permisoRepository.deleteById(id);
    }

}
