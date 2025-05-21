package Usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    private String nombre;
    private String cargo;
    private String correo;

}
