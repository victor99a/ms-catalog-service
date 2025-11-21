package com.victor.catalog.controller;


import com.victor.catalog.dto.CategoriaDTO;
import com.victor.catalog.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5174"})
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping
    public List<CategoriaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscar(@PathVariable long id) {
        return service.porId(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@RequestBody @Valid CategoriaDTO dto) {
        CategoriaDTO creada = service.crear(dto);
        URI uri = URI.create("/api/categorias/" + creada.id());
        return ResponseEntity.created(uri).body(creada);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Long id,
                                   @RequestBody @Valid CategoriaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
