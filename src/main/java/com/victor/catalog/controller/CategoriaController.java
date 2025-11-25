package com.victor.catalog.controller;


import com.victor.catalog.dto.CategoriaDTO;
import com.victor.catalog.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(
        name = "Categoria",
        description = "Operaciones sobre las categorias de la pastelería."
)
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping
    @Operation(
            summary = "Listar todas las categorias",
            description = "Devuelve el listado completo de todas las categorias"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado de categorias obtenido correctamente."
    )
    public List<CategoriaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar categoría por ID",
            description = "Devuelve una categoría existente según su identificador único."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría encontrada correctamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una categoría con el ID indicado."
            )
    })
    public CategoriaDTO buscar( @Parameter(
            description = "ID de la categoría a buscar.",
            example = "1"
    )@PathVariable long id) {
        return service.porId(id);
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva categoría",
            description = "Crea una nueva categoría en el catálogo. Normalmente requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoría creada correctamente."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autenticado (falta token JWT)."
            ),
    })
    public ResponseEntity<CategoriaDTO> crear(@RequestBody @Valid CategoriaDTO dto) {
        CategoriaDTO creada = service.crear(dto);
        URI uri = URI.create("/api/categorias/" + creada.id());
        return ResponseEntity.created(uri).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una categoría existente",
            description = "Actualiza los datos de una categoría ya registrada. Normalmente requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría actualizada correctamente."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una categoría con el ID indicado."
            )
    })
    public CategoriaDTO actualizar(@PathVariable Long id,
                                   @RequestBody @Valid CategoriaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una categoría",
            description = "Elimina una categoría existente del catálogo. Normalmente requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Categoría eliminada correctamente (sin contenido en la respuesta)."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una categoría con el ID indicado."
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
