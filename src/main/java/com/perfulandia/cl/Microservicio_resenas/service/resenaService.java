package com.perfulandia.cl.Microservicio_resenas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.repository.resenaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class resenaService {

    @Autowired
    private resenaRepository resenaRepository;
    
    public List<Resena> findAll(){
        return resenaRepository.findAll();
    } 

    public Resena findById(Integer idResena) {
        return resenaRepository.findById(idResena).get();
    }

    public Resena save(Resena nuevaResena) {
        return resenaRepository.save(nuevaResena);
    }

    public void delete(Resena idResena) {
        resenaRepository.delete(idResena);
    }
}
