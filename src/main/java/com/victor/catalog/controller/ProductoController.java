package com.victor.catalog.controller;


import com.victor.catalog.dto.ProductoDTO;
import com.victor.catalog.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5174"})
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> listar(){
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO buscar(@PathVariable long id){
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody @Valid ProductoDTO productoDTO){
        ProductoDTO creado = productoService.crear(productoDTO);
        // Location: /api/productos/{id}
        URI uri = URI.create("/api/productos/" + creado.id());
        return ResponseEntity.created(uri).body(creado);

    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id,
                                  @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
