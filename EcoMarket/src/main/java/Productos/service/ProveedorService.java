package Productos.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Productos.repository.ProveedorRepository;
import Productos.model.Proveedor;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}