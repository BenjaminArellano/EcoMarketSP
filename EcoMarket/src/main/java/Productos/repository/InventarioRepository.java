package Productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Productos.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
}