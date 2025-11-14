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

    private final ProductoRepository repo;
    private final CategoriaRepository categoriaRepo;
    private final ProductoMapper mapper;

    // LISTAR TODOS
    public List<ProductoDTO> listar() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // BUSCAR POR ID
    public ProductoDTO buscarPorId(Long id) {
        Producto entity = repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Producto no encontrado: " + id));

        return mapper.toDTO(entity);
    }

    // CREAR
    public ProductoDTO crear(ProductoDTO dto) {

        // 1) DTO -> entidad
        Producto entity = mapper.toEntity(dto);

        // 2) Buscar categoría y asignarla
        Categoria categoria = categoriaRepo.findById(dto.categoriaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoría no encontrada: " + dto.categoriaId()));

        entity.setCategoria(categoria);

        // 3) Guardar
        Producto guardado = repo.save(entity);

        // 4) Volver a DTO para la respuesta
        return mapper.toDTO(guardado);
    }

    // ACTUALIZAR

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {

        // 1) Buscar el producto existente en BD
        Producto entity = repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Producto no encontrado: " + id));

        // 2) Mapear los campos simples desde el DTO hacia la entidad
        //    (nombreProducto, descripcionProducto, precioProducto, etc.)
        mapper.updateEntityFromDTO(dto, entity);

        // 3) Actualizar la categoría si viene categoriaId en el DTO
        if (dto.categoriaId() != null) {
            Categoria categoria = categoriaRepo.findById(dto.categoriaId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Categoría no encontrada: " + dto.categoriaId()));
            entity.setCategoria(categoria);
        }

        // 4) Guardar cambios
        Producto guardado = repo.save(entity);

        // 5) Devolver el DTO actualizado
        return mapper.toDTO(guardado);
    }



    // ELIMINAR
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
