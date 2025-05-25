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

@RestController
@RequestMapping("/api/v1/resena")
public class resenaController {
    
    @Autowired
    private resenaService resenaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Resena>> listar(){
        List<Resena> listaResena = resenaService.findAll();
        if (listaResena.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResena);
            
        }
    } 

    @GetMapping("/PorCliente/{idCliente}")
    public ResponseEntity<List<Resena>> resenasPorCliente(@PathVariable int idCliente) {
        List<Resena> listaResenas = resenaService.findByIdCliente(idCliente);
        if (listaResenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResenas);
        }
    }

    @GetMapping("/PorProducto/{idProducto}")
    public ResponseEntity<List<Resena>> resenasPorProducto(@PathVariable Integer idProducto) {
        List<Resena> listaResenas = resenaService.findByIdProducto(idProducto);
        if (listaResenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResenas);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Resena> guardar(@RequestBody Resena nuevaResena) {
        Resena resenaGuardada = resenaService.save(nuevaResena);
        return ResponseEntity.ok(resenaGuardada);
    }

    @PutMapping("/actualizar/{idResena}")
    public ResponseEntity<Resena> actualizar(@PathVariable Integer idResena, @RequestBody Resena resenaActualizada) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        resenaActualizada.setIdResena(idResena);
        Resena resenaModificada = resenaService.save(resenaActualizada);
        return ResponseEntity.ok(resenaModificada);
    }

    @PatchMapping("/modificar/{idResena}")
    public ResponseEntity<Resena> modificar(@PathVariable Integer idResena, @RequestBody Resena resenaParcial) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        // Aquí podrías actualizar solo los campos que vienen en resenaParcial
        if (resenaParcial.getDescripcion() != null) {
            resenaExistente.setDescripcion(resenaParcial.getDescripcion());
        }
        // Agrega más campos según sea necesario
        Resena resenaModificada = resenaService.save(resenaExistente);
        return ResponseEntity.ok(resenaModificada);
    }

    @DeleteMapping("/eliminar/{idResena}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idResena) {
        Resena resenaExistente = resenaService.findById(idResena);
        if (resenaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        resenaService.delete(resenaExistente);
        return ResponseEntity.noContent().build();
    }

    


}
