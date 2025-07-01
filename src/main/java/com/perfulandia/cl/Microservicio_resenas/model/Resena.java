package com.perfulandia.cl.Microservicio_resenas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa una reseña de producto realizada por un cliente.
 */
@Entity
@Table(name="resena")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    /**
     * ID único de la reseña.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la reseña", example = "1")
    private Integer idResena;

    /**
     * Fecha en que se realizó la reseña.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(unique=false, length = 20,nullable=false)
    @Temporal(TemporalType.DATE)
    @Schema(description = "Fecha de la reseña", example = "2024-06-24")
    private Date fechaResena;

    /**
     * Descripción de la reseña.
     */
    @Column(unique=false, length = 200,nullable=false)
    @Schema(description = "Descripción de la reseña", example = "Excelente producto, muy recomendable.")
    private String descripcion;

    /**
     * Calificación númerica otorgada en la reseña.
     */
    @Column(unique=false, length = 10,nullable=false)
    @Schema(description = "Calificación otorgada en la reseña", example = "5")
    private Integer calificacion;

    /**
     * ID del cliente que realizó la reseña.
     */
    //@ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @Schema(description = "ID del cliente que realizó la reseña", example = "123")
    private int idCliente;
 
    /**
     * ID del producto reseñado.
     */
    //@ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @Schema(description = "ID del producto reseñado", example = "456")
    private Integer idProducto;
}
