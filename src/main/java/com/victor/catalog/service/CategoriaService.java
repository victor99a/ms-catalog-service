package com.victor.catalog.service;

import com.victor.catalog.dto.CategoriaDTO;
import com.victor.catalog.mapper.CategoriaMapper;
import com.victor.catalog.model.Categoria;
import com.victor.catalog.model.Producto;
import com.victor.catalog.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repo;
    private final CategoriaMapper mapper;

    public List<CategoriaDTO> listar() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }


    public CategoriaDTO porId(Long id) {
        Categoria c = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
        return mapper.toDTO(c);
    }

    public CategoriaDTO crear(CategoriaDTO dto) {
        Categoria entity = mapper.toEntity(dto);
        entity.setIdCategoria(null);
        return mapper.toDTO(repo.save(entity));
    }

    public CategoriaDTO actualizar(Long id, CategoriaDTO dto) {
        Categoria ex = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));

        ex.setNombreCategoria(dto.nombreCategoria());
        ex.setDescripcionCategoria(dto.descripcionCategoria());

        return mapper.toDTO(repo.save(ex));
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada: " + id);
        }
        repo.deleteById(id);
    }

}
