package com.victor.catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de una categoría del catálogo de la pastelería.")
public record CategoriaDTO(

        @Schema(
                description = "Identificador único de la categoría.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nombre visible de la categoría.",
                example = "Tortas"
        )
        String nombreCategoria,

        @Schema(
                description = "Descripción breve de la categoría.",
                example = "Categoría para tortas y pasteles"
        )
        String descripcionCategoria
) {}
