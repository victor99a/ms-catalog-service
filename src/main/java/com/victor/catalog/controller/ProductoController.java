package com.victor.catalog.controller;


import com.victor.catalog.dto.ProductoDTO;
import com.victor.catalog.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(
        name = "Productos",
        description = "Operaciones sobre el catálogo de productos de la pastelería."
)
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    @Operation(
            summary = "Listar todos los productos",
            description = "Devuelve el listado completo de productos disponibles en el catálogo."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado de productos obtenido correctamente."
    )
    public List<ProductoDTO> listar(){
        return productoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener pedido por ID",
            description = "Devuelve el detalle completo de un pedido existente."
    )
    public ProductoDTO buscar(  @Parameter(
            description = "ID del pedido a buscar",
            example = "5"
    )@PathVariable long id){
        return productoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un producto en el catálogo"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Producto creado correctamente.",
                    content = @Content(schema = @Schema(implementation = ProductoDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud."
            )
    })
    public ResponseEntity<ProductoDTO> crear(@RequestBody @Valid ProductoDTO productoDTO){
        ProductoDTO creado = productoService.crear(productoDTO);
        // Location: /api/productos/{id}
        URI uri = URI.create("/api/productos/" + creado.id());
        return ResponseEntity.created(uri).body(creado);

    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un producto existente",
            description = "Actualiza los datos de un producto ya registrado en el catálogo. Normalmente requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto actualizado correctamente.",
                    content = @Content(schema = @Schema(implementation = ProductoDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un producto con el ID indicado."
            )
    })
    public ProductoDTO actualizar(@PathVariable Long id,
                                  @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto existente del catálogo. Normalmente requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto eliminado correctamente (sin contenido en la respuesta)."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un producto con el ID indicado."
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
