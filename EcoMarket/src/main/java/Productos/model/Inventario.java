package com.ecomarket.productos.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    private Long id;
    private Integer stock;
    @OneToOne @JoinColumn(name = "producto_id") private Producto producto;
}