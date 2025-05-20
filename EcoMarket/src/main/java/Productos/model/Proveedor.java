package com.ecomarket.productos.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id 
    private Long id;
    private String nombre, contacto, telefono;
}