package rest;

import modelos.Cliente;
import servicios.ClienteServicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 27/02/16.
 */
@Path("/clientes")
public class ClienteRest {
    

    /**
     * Lista todos los clientes
     * @return List<Cliente>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() {
        return ClienteServicios.getClientes();
    }

    /**
     * Crea un cliente
     * @param cliente
     * @return
     */
    @POST
    @Consumes("application/json")
    public Cliente crearCliente(Cliente cliente) {
        ClienteServicios.agregarCliente(cliente);
        return cliente;
    }

    /**
     * Retorna un cliente de acuerdo al id recibido como parametro
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Cliente buscarCliente(@PathParam("id") Integer id) {
        return ClienteServicios.buscarCliente(id);
    }

    /**
     * Modifica un cliente
     * @param clienteModificado
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente modificarCliente(Cliente clienteModificado) {
        ClienteServicios.modificarCliente(clienteModificado);
        return clienteModificado;
    }

    /**
     * Eliminar cliente
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response eliminarCliente(@PathParam("id") Integer id) {
        ClienteServicios.eliminarCliente(id);
        return Response.status(200).build();
    }


}
