package com.ecomarket.productos.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resenia {
    @Id 
    private Long id;
    private Integer calificacion;
    private String comentario;
    @ManyToOne @JoinColumn(name = "producto_id") private Producto producto;
    @ManyToOne @JoinColumn(name = "cliente_id") private Cliente cliente;
}