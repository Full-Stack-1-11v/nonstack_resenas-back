package com.perfulandia.cl.Microservicio_resenas.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {

    public Integer id_producto;
    public String nombre_producto;
}
