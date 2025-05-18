package Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Usuarios.model.Empleado;

@Repository
public interface  EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
