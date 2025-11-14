package com.victor.catalog.mapper;

import com.victor.catalog.dto.ProductoDTO;
import com.victor.catalog.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE   // ignora lo que no se use
)
public interface ProductoMapper {

    // ENTITY -> DTO (para la respuesta)
    @Mapping(source = "categoria.idCategoria", target = "categoriaId")
    ProductoDTO toDTO(Producto entity);

    // DTO -> ENTITY (para crear)
    @Mapping(target = "categoria", ignore = true) // se setea en el service
    Producto toEntity(ProductoDTO dto);

    // DTO -> ENTITY (para actualizar)
    @Mapping(target = "categoria", ignore = true)
    void updateEntityFromDTO(ProductoDTO dto, @MappingTarget Producto entity);
}

