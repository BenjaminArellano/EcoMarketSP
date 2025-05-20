package Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pedidos.model.Factura;
import Pedidos.repository.FacturaRepository;


@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> obtenerTodos() {
        return facturaRepository.findAll();
    }

    public Factura ObtenerPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}
