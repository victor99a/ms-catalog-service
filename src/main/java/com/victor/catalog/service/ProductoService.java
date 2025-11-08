package com.victor.catalog.service;

import com.victor.catalog.dto.ProductoDTO;
import com.victor.catalog.mapper.ProductoMapper;
import com.victor.catalog.model.Categoria;
import com.victor.catalog.model.Producto;
import com.victor.catalog.repository.CategoriaRepository;
import com.victor.catalog.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo; // opcional si quieres validar categoría
    private final ProductoMapper mapper;

    public List<ProductoDTO> listar() {
        return productoRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /* BUSCAR POR ID */
    public ProductoDTO porId(Long id) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + id));
        return mapper.toDTO(p);
    }


    public ProductoDTO crear(ProductoDTO dto) {
        Producto entity = mapper.toEntity(dto);

        // (Opcional) Validar/asegurar referencia de categoría
        if (dto.categoriaId() != null) {
            // Si quieres validar existencia:
            Categoria cat = categoriaRepo.findById(dto.categoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + dto.categoriaId()));
            entity.setCategoria(cat);
            // Alternativa sin hit a DB: entity.setCategoria(new Categoria(dto.categoriaId(), null, null));
        }

        return mapper.toDTO(productoRepo.save(entity));
    }


    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto ex = productoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + id));

        // Copia de campos con MapStruct (incluye categoriaId -> categoria.id)
        mapper.updateEntityFromDTO(dto, ex);

        //Validar/ajustar referencia de categoría tras el update
        if (dto.categoriaId() != null) {
            Categoria cat = categoriaRepo.findById(dto.categoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + dto.categoriaId()));
            ex.setCategoria(cat);
        }

        return mapper.toDTO(productoRepo.save(ex));
    }


    public void eliminar(Long id) {
        if (!productoRepo.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado: " + id);
        }
        productoRepo.deleteById(id);
    }
}
