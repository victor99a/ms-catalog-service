package com.victor.catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un producto del catálogo.")
public record ProductoDTO(
        @Schema(description = "Identificador único del producto.", example = "1")
        Long id,

        @Schema(description = "Nombre visible del producto.", example = "Torta Mil Hojas")
        String nombreProducto,

        @Schema(description = "Descripción corta del producto.", example = "Torta de milhojas con manjar.")
        String descripcionProducto,

        @Schema(description = "Precio de venta en pesos chilenos.", example = "15000")
        double precioProducto,

        @Schema(description = "Categoria disponible", example = "7")
        Long categoriaId
)
{}
