package com.perfulandia.cl.Microservicio_resenas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.service.resenaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para la gestión de reseñas de productos.
 * Proporciona endpoints para operaciones CRUD y consultas personalizadas.
 */
@RestController
@RequestMapping("/api/v1/resena")
@Tag(name = "Reseñas", description = "API para gestionar reseñas de productos")
public class resenaController {
    
    @Autowired
    private resenaService resenaService;

    /**
     * Obtiene una lista de todas las reseñas disponibles en el sistema.
     * 
     * @return Lista de objetos Resena.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener todas las reseñas", description ="Obtiene una lista de todas las reseñas disponibles en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Resena.class)),
                examples = @ExampleObject(
                    value = "[{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Excelente producto, muy recomendable.\",\"calificacion\":5,\"idCliente\":123,\"idProducto\":456}]"
                )
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay reseñas disponibles",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<List<Resena>> listar(){
        List<Resena> listaResena = resenaService.findAll();
        if (listaResena.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResena);
        }
    } 

    /**
     * Obtiene una lista de reseñas filtradas por el ID del cliente.
     * 
     * @param idCliente ID del cliente.
     * @return Lista de objetos Resena asociados al cliente.
     */
    @Parameter(description = "ID del cliente para filtrar las reseñas",required = true)
    @GetMapping("/PorCliente/{idCliente}")
    @Operation(summary = "Obtener reseñas por cliente", description ="Obtiene una lista de las reseñas por la id del cliente.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Resena.class)),
                examples = @ExampleObject(
                    value = "[{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Excelente producto, muy recomendable.\",\"calificacion\":5,\"idCliente\":123,\"idProducto\":456}]"
                )
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay reseñas disponibles para el cliente",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<List<Resena>> resenasPorCliente(@PathVariable int idCliente) {
        List<Resena> listaResenas = resenaService.findByIdCliente(idCliente);
        if (listaResenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResenas);
        }
    }

    /**
     * Obtiene una lista de reseñas filtradas por el ID del producto.
     * 
     * @param idProducto ID del producto.
     * @return Lista de objetos Resena asociados al producto.
     */
    @Parameter(description = "ID del producto para filtrar las reseñas", required = true)
    @GetMapping("/PorProducto/{idProducto}")
    @Operation(summary = "Obtener reseñas por producto", description ="Obtiene una lista de las reseñas por la id del producto.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Resena.class)),
                examples = @ExampleObject(
                    value = "[{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Excelente producto, muy recomendable.\",\"calificacion\":5,\"idCliente\":123,\"idProducto\":456}]"
                )
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay reseñas disponibles para el producto",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<List<Resena>> resenasPorProducto(@PathVariable Integer idProducto) {
        List<Resena> listaResenas = resenaService.findByIdProducto(idProducto);
        if (listaResenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResenas);
        }
    }

    /**
     * Guarda una nueva reseña en el sistema.
     * 
     * @param nuevaResena Objeto Resena a guardar.
     * @return La reseña guardada.
     */
    @PostMapping("/guardar")
    @Operation(summary = "Guardar una nueva reseña", description = "Guarda una nueva reseña en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reseña guardada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Resena.class),
                examples = @ExampleObject(
                    value = "{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Excelente producto, muy recomendable.\",\"calificacion\":5,\"idCliente\":123,\"idProducto\":456}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<Resena> guardar(@RequestBody Resena nuevaResena) {
        Resena resenaGuardada = resenaService.save(nuevaResena);
        return ResponseEntity.ok(resenaGuardada);
    }

    /**
     * Actualiza una reseña existente por su ID.
     * 
     * @param idResena ID de la reseña a actualizar.
     * @param resenaActualizada Objeto Resena con los nuevos datos.
     * @return La reseña actualizada.
     */
    @Parameter(description = "ID de la reseña a actualizar", required = true)
    @PutMapping("/actualizar/{idResena}")
    @Operation(summary = "Actualizar una reseña", description = "Actualiza una reseña existente por su ID.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reseña actualizada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Resena.class),
                examples = @ExampleObject(
                    value = "{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Excelente producto actualizado.\",\"calificacion\":4,\"idCliente\":123,\"idProducto\":456}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<Resena> actualizar(@PathVariable Integer idResena, @RequestBody Resena resenaActualizada) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        resenaActualizada.setIdResena(idResena);
        Resena resenaModificada = resenaService.save(resenaActualizada);
        return ResponseEntity.ok(resenaModificada);
    }

    /**
     * Modifica parcialmente los campos de una reseña existente por su ID.
     * 
     * @param idResena ID de la reseña a modificar.
     * @param resenaParcial Objeto Resena con los campos a modificar.
     * @return La reseña modificada.
     */
    @Parameter(description = "ID de la reseña a modificar", required = true)
    @PatchMapping("/modificar/{idResena}")
    @Operation(summary = "Modificar parcialmente una reseña", description = "Modifica parcialmente los campos de una reseña existente por su ID.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reseña modificada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Resena.class),
                examples = @ExampleObject(
                    value = "{\"idResena\":1,\"fechaResena\":\"2024-06-24\",\"descripcion\":\"Producto modificado.\",\"calificacion\":5,\"idCliente\":123,\"idProducto\":456}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<Resena> modificar(@PathVariable Integer idResena, @RequestBody Resena resenaParcial) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        // actualizar solo los campos que vienen en resenaParcial
        if (resenaParcial.getDescripcion() != null) {
            resenaExistente.setDescripcion(resenaParcial.getDescripcion());
        }
        // Agrega más campos según sea necesario
        Resena resenaModificada = resenaService.save(resenaExistente);
        return ResponseEntity.ok(resenaModificada);
    }

    /**
     * Elimina una reseña existente por su ID.
     * 
     * @param idResena ID de la reseña a eliminar.
     * @return Respuesta sin contenido si la eliminación fue exitosa.
     */
    @Parameter(description = "ID de la reseña a eliminar", required = true)
    @DeleteMapping("/eliminar/{idResena}")
    @Operation(summary = "Eliminar una reseña", description = "Elimina una reseña existente por su ID.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Reseña eliminada exitosamente",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer idResena) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        resenaService.delete(resenaExistente);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene el promedio de calificación de las reseñas para un producto específico.
     * 
     * @param idProducto ID del producto.
     * @return Promedio de calificación como Double.
     */
    @Parameter(description = "ID del producto para calcular el promedio de calificación", required = true)
    @GetMapping("/promedioPorProducto/{idProducto}")
    @Operation(summary = "Obtener promedio de calificación por producto", description = "Obtiene el promedio de calificación de las reseñas para un producto específico.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Double.class),
                examples = @ExampleObject(
                    value = "4.5"
                )
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay reseñas disponibles para el producto",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud incorrecta",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
        )
    })
    public ResponseEntity<Double> promedioPorProducto(@PathVariable Integer idProducto) {
        Double promedio = resenaService.findPromedioCalificacionPorProducto(idProducto);
        if (promedio == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(promedio);
        }
    }

}
