package com.perfulandia.cl.Microservicio_resenas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="resena")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResena;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(unique=false, length = 20,nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaResena;

    @Column(unique=false, length = 200,nullable=false)
    private String descripcion;

    @Column(unique=false, length = 10,nullable=false)
    private Integer calificacion;

    //@ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private int idCliente;
 
    //@ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Integer idProducto;



}
