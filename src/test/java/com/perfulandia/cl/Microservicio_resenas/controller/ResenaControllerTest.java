package com.perfulandia.cl.Microservicio_resenas.controller;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.springframework.http.MediaType;
import java.util.Date;
import java.util.List;
import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.service.resenaService;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.perfulandia.cl.Microservicio_resenas.controller.resenaController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@WebMvcTest(resenaController.class)
public class ResenaControllerTest {

    @Autowired MockMvc mockMvc;

    @MockitoBean
    private resenaService resenaService;

    @Autowired
    private resenaController resenaController;

    ObjectMapper objectMapper = new ObjectMapper();
    

    @Test
    public void testListar() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        when(resenaService.findAll()).thenReturn(List.of(resena));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resena/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idResena").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Excelente producto, muy recomendable."))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].calificacion").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idCliente").value(123))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idProducto").value(456));
    }

    @Test
    public void testResenasPorCliente() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        when(resenaService.findByIdCliente(123)).thenReturn(List.of(resena));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resena/PorCliente/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idCliente").value(123));
    }

    @Test
    public void testResenasPorProducto() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        when(resenaService.findByIdProducto(456)).thenReturn(List.of(resena));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resena/PorProducto/456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idProducto").value(456));
    }

    @Test
    public void testGuardar() throws Exception {
        Resena resena = new Resena(1, new Date(), "Excelente producto, muy recomendable.", 5, 123, 456);
        when(resenaService.save(any(Resena.class))).thenReturn(resena);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/resena/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resena)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idResena").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Excelente producto, muy recomendable."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.calificacion").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCliente").value(123))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProducto").value(456));
    }

    @Test
    public void testActualizar() throws Exception {
        Resena existente = new Resena(1, new Date(), "Vieja descripcion", 4, 123, 456);
        Resena actualizada = new Resena(1, new Date(), "Excelente producto actualizado.", 4, 123, 456);

        when(resenaService.findById(1)).thenReturn(existente);
        when(resenaService.save(any(Resena.class))).thenReturn(actualizada);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/resena/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Excelente producto actualizado."));
    }

    @Test
    public void testModificar() throws Exception {
        Resena existente = new Resena(1, new Date(), "Vieja descripcion", 5, 123, 456);
        Resena parcial = new Resena();
        parcial.setDescripcion("Producto modificado.");

        when(resenaService.findById(1)).thenReturn(existente);
        when(resenaService.save(any(Resena.class))).thenReturn(
            new Resena(1, new Date(), "Producto modificado.", 5, 123, 456)
        );

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/resena/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parcial)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Producto modificado."));
    }

    @Test
    public void testEliminar() throws Exception {
        Resena existente = new Resena(1, new Date(), "desc", 1, 1, 1);
        when(resenaService.findById(1)).thenReturn(existente);
        doNothing().when(resenaService).delete(existente);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/resena/eliminar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(resenaService, times(1)).delete(existente);
    }

    @Test
    public void testPromedioPorProducto() throws Exception {
        when(resenaService.findPromedioCalificacionPorProducto(456)).thenReturn(4.5);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resena/promedioPorProducto/456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4.5"));
    }
}
