package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Producto;
import org.acme.service.ProductoService;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoController {

    @Inject
    ProductoService service;

    @GET
    public List<Producto> getAll() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        return service.buscarPorId(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Producto producto) {
        service.crear(producto);
        return Response.status(Response.Status.CREATED).entity(producto).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Producto producto) {
        service.actualizar(id, producto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = service.eliminar(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}