package com.perfulandia.cl.Microservicio_resenas.model;

import java.util.Date;

import com.perfulandia.cl.Microservicio_resenas.dto.ClienteDTO;
import com.perfulandia.cl.Microservicio_resenas.dto.ProductoDTO;

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

    @Column(unique=false, length = 20,nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaResena;

    @Column(unique=false, length = 200,nullable=false)
    private String descripcion;

    @Column(unique=false, length = 20,nullable=false)
    private String idProducto;

    @Column(unique=false, length = 20,nullable=false)
    private String idCliente;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ClienteDTO cliente;
 
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ProductoDTO producto;



}
