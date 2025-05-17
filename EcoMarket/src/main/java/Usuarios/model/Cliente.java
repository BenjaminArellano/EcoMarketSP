package Usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private Long id;
    
    private String nombre;
    private String correo;
    private String direccionEnvio;
    private String telefono;

}
