package rest;

import modelos.Producto;
import servicios.ProductoServicios;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Path("/productos")
public class ProductoRest {

    @Inject
    private ProductoServicios productoServicios;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listarProductos() {
        return productoServicios.getProductos();
    }


    @POST
    @Consumes("application/json")
    public Response crearProducto(Producto producto) throws Exception {
        return productoServicios.agregarProducto(producto);

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Producto buscarProducto(@PathParam("id") Integer id) {
        return productoServicios.buscarProducto(id);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Producto modificarProducto(Producto productoModificado) {
        return productoServicios.modificarProducto(productoModificado);
    }


    @DELETE
    @Path("{id}")
    public Response eliminarProducto(@PathParam("id") Integer id) {
        productoServicios.eliminarProducto(id);
        return Response.status(200).build();
    }
}
