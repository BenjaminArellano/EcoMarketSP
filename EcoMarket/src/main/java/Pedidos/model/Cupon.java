package Pedidos.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {

    @Id
    private Long id;

    private String codigo;
    private int descuento;

    @OneToMany(mappedBy = "cupon")
    private List<Pedido> pedidos; 

}
