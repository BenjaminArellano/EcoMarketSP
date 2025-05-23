package Productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Productos.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
}