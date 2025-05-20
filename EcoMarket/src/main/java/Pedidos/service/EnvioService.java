package Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pedidos.model.Envio;
import Pedidos.repository.EnvioRepository;


@Service
public class EnvioService {


    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    public Envio ObtenerPorId(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio guardar(Envio envio) {
        return envioRepository.save(envio);
    }

    public void eliminar(Long id) {
        envioRepository.deleteById(id);
    }
}
