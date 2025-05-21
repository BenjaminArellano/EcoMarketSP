package Pedidos.model;

import java.time.LocalDateTime;
import java.util.List;

import Usuarios.model.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    private Long id;

    private LocalDateTime fecha;
    private String estado;
    private int total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalle;

    @ManyToOne
    @JoinColumn(name = "cupon_id") 
    private Cupon cupon;

}
