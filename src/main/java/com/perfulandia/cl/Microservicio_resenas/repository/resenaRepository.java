package com.perfulandia.cl.Microservicio_resenas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;

@Repository
public interface resenaRepository extends JpaRepository<Resena, Integer> {

    List<Resena> findByIdCliente(int idCliente);

    List<Resena> findByIdProducto(Integer idProducto);

}
