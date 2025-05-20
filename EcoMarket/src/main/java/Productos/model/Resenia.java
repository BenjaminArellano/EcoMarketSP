package Productos.model;

import Usuarios.model.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resenia {

    @Id 
    private Long id;

    private int calificacion;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "producto_id") 
    private Producto producto;

    @ManyToOne 
    @JoinColumn(name = "cliente_id") 
    private Cliente cliente;
}