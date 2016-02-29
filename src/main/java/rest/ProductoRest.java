package rest;

import modelos.Cliente;
import modelos.Producto;
import servicios.ClienteServicios;
import servicios.ProductoServicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Path("/productos")
public class ProductoRest {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listarProductos() {
        return ProductoServicios.getProductos();
    }


    @POST
    @Consumes("application/json")
    public Producto crearProducto(Producto producto) {
        ProductoServicios.agregarProducto(producto);
        return producto;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Producto buscarProducto(@PathParam("id") Integer id) {
        return ProductoServicios.buscarProducto(id);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Producto modificarProducto(Producto productoModificado) {
        ProductoServicios.modificarProducto(productoModificado);
        return productoModificado;
    }


    @DELETE
    @Path("{id}")
    public Response eliminarProducto(@PathParam("id") Integer id) {
        ProductoServicios.eliminarProducto(id);
        return Response.status(200).build();
    }
}
