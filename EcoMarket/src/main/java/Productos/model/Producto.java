package Productos.model;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id 
    private Long id;
    private String nombre, descripcion, categoria;
    private Double precio;
    @ManyToOne @JoinColumn(name = "proveedor_id") private Proveedor proveedor;
}