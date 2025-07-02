package com.perfulandia.cl.Microservicio_resenas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.service.resenaService;
import com.perfulandia.cl.Microservicio_resenas.assemblers.ResenaModelAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResenaControllerV2.class)
public class ResenaControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private resenaService resenaService;

    @MockBean
    private ResenaModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListar() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        List<Resena> resenas = List.of(resena);
        when(resenaService.findAll()).thenReturn(resenas);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(resena));

        mockMvc.perform(get("/api/v2/resena/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.resenaList[0].idResena").value(1))
                .andExpect(jsonPath("$._embedded.resenaList[0].descripcion").value("Excelente producto, muy recomendable."))
                .andExpect(jsonPath("$._embedded.resenaList[0].calificacion").value(5))
                .andExpect(jsonPath("$._embedded.resenaList[0].idCliente").value(123))
                .andExpect(jsonPath("$._embedded.resenaList[0].idProducto").value(456));
    }

    @Test
    public void testResenasPorCliente() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        List<Resena> resenas = List.of(resena);
        when(resenaService.findByIdCliente(123)).thenReturn(resenas);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(resena));

        mockMvc.perform(get("/api/v2/resena/PorCliente/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.resenaList[0].idCliente").value(123));
    }

    @Test
    public void testResenasPorProducto() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        List<Resena> resenas = List.of(resena);
        when(resenaService.findByIdProducto(456)).thenReturn(resenas);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(resena));

        mockMvc.perform(get("/api/v2/resena/PorProducto/456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.resenaList[0].idProducto").value(456));
    }

    @Test
    public void testGuardar() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        when(resenaService.save(any(Resena.class))).thenReturn(resena);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(resena));

        mockMvc.perform(post("/api/v2/resena/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resena)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.descripcion").value("Excelente producto, muy recomendable."))
                .andExpect(jsonPath("$.calificacion").value(5))
                .andExpect(jsonPath("$.idCliente").value(123))
                .andExpect(jsonPath("$.idProducto").value(456));
    }

    @Test
    public void testActualizar() throws Exception {
        Resena existente = new Resena(1, new Date(), "Vieja descripcion", 4, 123, 456);
        Resena actualizada = new Resena(1, new Date(), "Excelente producto actualizado.", 4, 123, 456);

        when(resenaService.findById(1)).thenReturn(existente);
        when(resenaService.save(any(Resena.class))).thenReturn(actualizada);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(actualizada));

        mockMvc.perform(put("/api/v2/resena/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Excelente producto actualizado."));
    }

    @Test
    public void testModificar() throws Exception {
        Resena existente = new Resena(1, new Date(), "Vieja descripcion", 5, 123, 456);
        Resena parcial = new Resena();
        parcial.setDescripcion("Producto modificado.");

        Resena modificado = new Resena(1, new Date(), "Producto modificado.", 5, 123, 456);

        when(resenaService.findById(1)).thenReturn(existente);
        when(resenaService.save(any(Resena.class))).thenReturn(modificado);
        when(assembler.toModel(any(Resena.class))).thenReturn(EntityModel.of(modificado));

        mockMvc.perform(patch("/api/v2/resena/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parcial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Producto modificado."));
    }

    @Test
    public void testEliminar() throws Exception {
        Resena existente = new Resena(1, new Date(), "desc", 1, 1, 1);
        when(resenaService.findById(1)).thenReturn(existente);
        Mockito.doNothing().when(resenaService).delete(existente);

        mockMvc.perform(delete("/api/v2/resena/eliminar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPromedioPorProducto() throws Exception {
        when(resenaService.findPromedioCalificacionPorProducto(456)).thenReturn(4.5);

        mockMvc.perform(get("/api/v2/resena/promedioPorProducto/456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("4.5"));
    }
}
