package rest;

import modelos.Proveedor;
import servicios.ProveedorServicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Path("/proveedores")
public class ProveedorRest {

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proveedor> listarClientes() {
        return ProveedorServicios.getProveedores();
    }

    /**
     *
     * @param proveedor
     * @return
     */
    @POST
    @Consumes("application/json")
    public Proveedor crearProveedor(Proveedor proveedor) {
        ProveedorServicios.agregarProveedor(proveedor);
        return proveedor;
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Proveedor buscarProveedor(@PathParam("id") Integer id) {
        return ProveedorServicios.buscarProveedor(id);
    }

    /**
     *
     * @param proveedorModificado
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Proveedor modificarProveedor(Proveedor proveedorModificado) {
        ProveedorServicios.modificarProveedor(proveedorModificado);
        return proveedorModificado;
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response eliminarProveedor(@PathParam("id") Integer id) {
        ProveedorServicios.eliminarProveedor(id);
        return Response.status(200).build();
    }


}
