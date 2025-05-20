package Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pedidos.model.DetallePedido;

@Repository
public interface  DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {


}
