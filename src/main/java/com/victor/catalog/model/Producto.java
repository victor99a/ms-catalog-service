package com.victor.catalog.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Producto {
    @Id @GeneratedValue private Long id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @Column(name = "precio_producto")
    private double precioProducto;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @Column(name = "imagen_url")
    private String imagenUrl;
}
