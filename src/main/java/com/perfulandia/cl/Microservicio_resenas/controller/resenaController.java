package com.perfulandia.cl.Microservicio_resenas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.service.resenaService;

@RestController
@RequestMapping("api/v1/resena")
public class resenaController {
    
    @Autowired
    private resenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> listar(){
        List<Resena> listaResena = resenaService.findAll();
        if (listaResena.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaResena);
            
        }
    } 

    


}
