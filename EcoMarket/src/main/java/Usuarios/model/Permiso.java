package Usuarios.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permiso {

    @Id
    private Long id;
    
    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "permisos")
    private Set<Rol> roles;

}
