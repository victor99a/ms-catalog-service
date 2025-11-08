package com.victor.catalog.mapper;

import com.victor.catalog.dto.CategoriaDTO;
import com.victor.catalog.model.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDTO(Categoria c);

    Categoria toEntity(CategoriaDTO dto);

    List<CategoriaDTO> toDTOList(List<Categoria> list);
}
