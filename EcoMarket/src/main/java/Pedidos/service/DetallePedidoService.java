package Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pedidos.model.DetallePedido;
import Pedidos.repository.DetallePedidoRepository;


@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> obtenerTodos() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido ObtenerPorId(Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public DetallePedido guardar(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public void eliminar(Long id) {
    detallePedidoRepository.deleteById(id);
    }
}
