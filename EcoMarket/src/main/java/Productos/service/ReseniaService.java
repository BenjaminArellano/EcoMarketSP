package Productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Productos.model.Resenia;
import Productos.repository.ReseniaRepository;

@Service
public class ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;

    public List<Resenia> obtenerTodos() {
        return reseniaRepository.findAll();
    }

    public Resenia obtenerPorId(Long id) {
        return reseniaRepository.findById(id).orElse(null);
    }

    public Resenia guardar(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    public void eliminar(Long id) {
        reseniaRepository.deleteById(id);
    }
}