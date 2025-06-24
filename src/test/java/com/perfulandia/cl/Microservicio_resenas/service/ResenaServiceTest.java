package com.perfulandia.cl.Microservicio_resenas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.perfulandia.cl.Microservicio_resenas.repository.resenaRepository;
import com.perfulandia.cl.Microservicio_resenas.service.resenaService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;


@SpringBootTest
public class ResenaServiceTest {

    @Autowired
    private resenaService resenaService;

    @MockitoBean
    private resenaRepository resenaRepository;

    @Test
    public void testFindAll() {
        when(resenaRepository.findAll()).thenReturn(List.of(new Resena(1, new Date(),"Excelente producto",1,1,1)));

        List<Resena> resenas = resenaService.findAll();

        assertEquals(resenas.size(), 1);

        assertNotNull(resenas);

    }

    @Test
    public void testFindByIdCliente() {
        when(resenaRepository.findByIdCliente(1)).thenReturn(List.of(new Resena(1, new Date(),"Excelente producto",1,1,1)));

        List<Resena> resenas = resenaService.findByIdCliente(1);

        assertEquals(resenas.size(), 1);

        assertNotNull(resenas);
   
    }

    @Test
    public void testFindByIdProducto() {
        when(resenaRepository.findByIdProducto(1)).thenReturn(List.of(new Resena(1, new Date(),"Excelente producto",1,1,1)));

        List<Resena> resenas = resenaService.findByIdProducto(1);

        assertEquals(resenas.size(), 1);

        assertNotNull(resenas);
    }

    @Test
    public void testFindById() {
        when(resenaRepository.findById(1)).thenReturn(Optional.empty());

        
    }

    @Test 
    public void testSave() {
        Resena nuevaResena = new Resena(1, new Date(),"Excelente producto",1,1,1);
        when(resenaRepository.save(nuevaResena)).thenReturn(nuevaResena);

        Resena resenaGuardada = resenaService.save(nuevaResena);

        assertNotNull(resenaGuardada);
        assertEquals(resenaGuardada.getIdResena(), nuevaResena.getIdResena());
    }

    @Test
    public void testDelete() {
        Resena resena = new Resena(1, new Date(),"Excelente producto",1,1,1);
        resenaService.delete(resena);

       verify(resenaRepository, times(1)).delete(resena);

    }
    
    @Test
    public void testFindPromedioCalificacionPorProducto() {
        when(resenaRepository.findPromedioCalificacionPorProducto(1)).thenReturn(4.5);

        Double promedio = resenaService.findPromedioCalificacionPorProducto(1);

        assertNotNull(promedio);
        assertEquals(4.5, promedio);
    }

}
