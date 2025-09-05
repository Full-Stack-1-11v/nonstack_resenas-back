package com.perfulandia.cl.Microservicio_resenas.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.perfulandia.cl.Microservicio_resenas.model.Resena;
import com.perfulandia.cl.Microservicio_resenas.controller.resenaController;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<Resena>> {

    @Override
    public EntityModel<Resena> toModel(Resena resena) {
        return EntityModel.of(resena,
            linkTo(methodOn(resenaController.class).listar()).withRel("resenas"),
            linkTo(methodOn(resenaController.class).resenasPorCliente(resena.getIdCliente())).withRel("porCliente"),
            linkTo(methodOn(resenaController.class).resenasPorProducto(resena.getIdProducto())).withRel("porProducto"),
            linkTo(methodOn(resenaController.class).promedioPorProducto(resena.getIdProducto())).withRel("promedioProducto")
        );
    }
}
