package com.perfulandia.cl.Microservicio_resenas.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

    public Integer id_cliente;
    public String nombre_cliente;

}