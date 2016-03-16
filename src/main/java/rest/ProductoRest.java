package rest;

import excepciones.NoExisteProductoException;
import excepciones.NoExisteProveedorException;
import excepciones.TamanoPaginaExcepcion;
import modelos.Producto;
import modelos.ProductoDuplicado;
import servicios.ProductoDuplicadoServicios;
import paginacion.Paginacion;
import servicios.ProductoServicios;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/paginado")
    public List<Producto> listarProductos(Paginacion paginacion) throws TamanoPaginaExcepcion{
        return productoServicios.getProductos(paginacion);
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
    public Producto buscarProducto(@PathParam("id") Integer id) throws NoExisteProductoException {
        return productoServicios.buscarProducto(id);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Producto modificarProducto(Producto productoModificado) throws NoExisteProveedorException {
        return productoServicios.modificarProducto(productoModificado);
    }


    @DELETE
    @Path("{id}")
    public Response eliminarProducto(@PathParam("id") Integer id) {
        productoServicios.eliminarProducto(id);
        return Response.status(200).build();
    }
}
