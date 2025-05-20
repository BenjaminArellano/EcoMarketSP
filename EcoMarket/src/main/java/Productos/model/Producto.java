package Productos.model;



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
public class Producto {

    @Id 
    private Long id;

    private String nombre, descripcion, categoria;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "proveedor_id") 
    private Proveedor proveedor;
}