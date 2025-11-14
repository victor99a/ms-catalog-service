package com.victor.catalog.mapper;

import com.victor.catalog.dto.CategoriaDTO;
import com.victor.catalog.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    // ENTITY -> DTO
    @Mapping(source = "idCategoria", target = "id")
    CategoriaDTO toDTO(Categoria entity);

    // DTO -> ENTITY (para crear, ignoramos el id porque lo genera la BD)
    @Mapping(target = "idCategoria", ignore = true)
    Categoria toEntity(CategoriaDTO dto);

    // UPDATE (no tocamos el id)
    @Mapping(target = "idCategoria", ignore = true)
    void updateFromDTO(CategoriaDTO dto, @MappingTarget Categoria entity);
}
