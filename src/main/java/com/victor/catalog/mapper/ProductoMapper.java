package com.victor.catalog.mapper;

import com.victor.catalog.dto.ProductoDTO;
import com.victor.catalog.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "categoria.id", target = "categoriaId")
    ProductoDTO toDTO(Producto p);

    @Mapping(source = "categoriaId", target = "categoria.id")
    Producto toEntity(ProductoDTO dto);

    @Mapping(source = "categoriaId", target = "categoria.id")
    void updateEntityFromDTO(ProductoDTO dto, @MappingTarget Producto entity);
}

