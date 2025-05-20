package Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pedidos.model.Cupon;


@Repository
public interface  CuponRepository extends JpaRepository<Cupon, Long> {

}
