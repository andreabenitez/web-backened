package rest;

import excepciones.NoExisteProveedorException;
import modelos.Proveedor;
import servicios.ProveedorServicios;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Path("/proveedores")
public class ProveedorRest {

    @Inject
    private ProveedorServicios proveedorServicios;

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proveedor> listarClientes() {
        return proveedorServicios.getProveedores();
    }

    /**
     *
     * @param proveedor
     * @return
     */
    @POST
    @Consumes("application/json")
    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorServicios.agregarProveedor(proveedor);

    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Proveedor buscarProveedor(@PathParam("id") Integer id) throws NoExisteProveedorException {
        return proveedorServicios.buscarProveedor(id);
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
        return proveedorServicios.modificarProveedor(proveedorModificado);

    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response eliminarProveedor(@PathParam("id") Integer id) {
        proveedorServicios.eliminarProveedor(id);
        return Response.status(200).build();
    }


}
