package com.perfulandia.cl.Microservicio_resenas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;

/**
 * Repositorio para la entidad Resena.
 * Proporciona métodos para acceder y manipular reseñas en la base de datos.
 */
@Repository
public interface resenaRepository extends JpaRepository<Resena, Integer> {

    /**
     * Busca todas las reseñas realizadas por un cliente específico.
     * @param idCliente ID del cliente.
     * @return Lista de reseñas asociadas al cliente.
     */
    List<Resena> findByIdCliente(int idCliente);

    /**
     * Busca todas las reseñas asociadas a un producto específico.
     * @param idProducto ID del producto.
     * @return Lista de reseñas asociadas al producto.
     */
    List<Resena> findByIdProducto(Integer idProducto);

    /**
     * Obtiene el promedio de calificación de las reseñas para un producto específico.
     * @param idProducto ID del producto.
     * @return Promedio de calificación (Double) o null si no hay reseñas.
     */
    @Query("SELECT AVG(r.calificacion) FROM Resena r WHERE r.idProducto = :idProducto")
    Double findPromedioCalificacionPorProducto(Integer idProducto);

}
