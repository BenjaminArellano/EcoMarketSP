package Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Usuarios.model.Permiso;

@Repository
public interface  PermisoRepository extends JpaRepository<Permiso, Long> {


}
