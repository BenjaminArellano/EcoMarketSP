package Pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    private Long id;

    private String direccion;
    private String estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
