package Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Usuarios.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {


}
