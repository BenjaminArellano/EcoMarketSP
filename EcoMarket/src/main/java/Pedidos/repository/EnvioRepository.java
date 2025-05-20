package Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pedidos.model.Envio;

@Repository
public interface  EnvioRepository extends JpaRepository<Envio, Long> {
    

}
