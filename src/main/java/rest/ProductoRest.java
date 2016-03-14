package rest;

import modelos.Producto;
import modelos.ProductoDuplicado;
import servicios.ProductoDuplicadoServicios;
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

    @Inject
    private ProductoDuplicadoServicios productoDuplicadoServicios;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listarProductos() {
        return productoServicios.getProductos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/duplicados")
    public List<ProductoDuplicado> listarProductosDuplicados(){
        return productoDuplicadoServicios.getProductosDuplicados();

    }

    @POST
    @Consumes("application/json")
    public Response crearProducto(Producto producto) throws Exception {
        return productoServicios.agregarProducto(producto);

    }

    @POST
    @Consumes("application/json")
    @Path("/insertalista")
    public Response insertarListaDeProductos(List<Producto> producto)throws Exception{

        productoDuplicadoServicios.agregarListaDeProductos(producto);
        return Response.status(200).build();
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
