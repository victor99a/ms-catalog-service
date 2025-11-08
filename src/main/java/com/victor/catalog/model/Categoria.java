package com.victor.catalog.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity @Data
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;
}
