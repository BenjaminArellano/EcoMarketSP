package Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pedidos.model.Cupon;
import Pedidos.repository.CuponRepository;


@Service
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    public List<Cupon> obtenerTodos() {
        return cuponRepository.findAll();
    }

    public Cupon ObtenerPorId(Long id) {
        return cuponRepository.findById(id).orElse(null);
    }

    public Cupon guardar(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    public void eliminar(Long id) {
        cuponRepository.deleteById(id);
    }
}
