package Pedidos.model;

import java.time.LocalDateTime;

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
public class Factura {

    @Id
    private Long id;

    private LocalDateTime fecha;
    private int total;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
