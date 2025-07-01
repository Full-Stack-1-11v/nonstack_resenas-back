package com.perfulandia.cl.Microservicio_resenas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.repository.resenaRepository;

import jakarta.transaction.Transactional;

/**
 * Servicio para la gestión de reseñas.
 * Proporciona métodos para operaciones CRUD y consultas personalizadas sobre reseñas.
 */
@Service
@Transactional
public class resenaService {

    @Autowired
    private resenaRepository resenaRepository;
    
    /**
     * Obtiene todas las reseñas almacenadas en la base de datos.
     * @return Lista de todas las reseñas.
     */
    public List<Resena> findAll(){
        return resenaRepository.findAll();
    } 

    /**
     * Busca todas las reseñas realizadas por un cliente específico.
     * @param idCliente ID del cliente.
     * @return Lista de reseñas asociadas al cliente.
     */
    public List<Resena> findByIdCliente(int idCliente) {
        return resenaRepository.findByIdCliente(idCliente); //busco reseñas por la id del cliente
    }

    /**
     * Busca todas las reseñas asociadas a un producto específico.
     * @param idProducto ID del producto.
     * @return Lista de reseñas asociadas al producto.
     */
    public List<Resena> findByIdProducto(Integer idProducto) {
        return resenaRepository.findByIdProducto(idProducto); //busco reseñas por la id del producto
    }

    /**
     * Busca una reseña por su ID.
     * @param idResena ID de la reseña.
     * @return La reseña encontrada o lanza una excepción si no existe.
     */
    public Resena findById(Integer idResena) {
        return resenaRepository.findById(idResena).get(); 
    }

    /**
     * Guarda una nueva reseña o actualiza una existente.
     * @param nuevaResena Objeto Resena a guardar.
     * @return La reseña guardada.
     */
    public Resena save(Resena nuevaResena) {
        return resenaRepository.save(nuevaResena);
    }

    /**
     * Elimina una reseña de la base de datos.
     * @param idResena Objeto Resena a eliminar.
     */
    public void delete(Resena idResena) {
        resenaRepository.delete(idResena);
    }

    /**
     * Obtiene el promedio de calificación de las reseñas para un producto específico.
     * @param idProducto ID del producto.
     * @return Promedio de calificación (Double) o null si no hay reseñas.
     */
    public Double findPromedioCalificacionPorProducto(Integer idProducto) {
        return resenaRepository.findPromedioCalificacionPorProducto(idProducto);
    }

}
