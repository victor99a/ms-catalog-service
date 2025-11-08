package com.victor.catalog.dto;

public record ProductoDTO(
        Long id,
        String nombreProducto,
        String descripcionProducto,
        double precioProducto,
        Long categoriaId
)
{}
