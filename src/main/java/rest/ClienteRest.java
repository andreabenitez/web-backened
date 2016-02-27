package rest;

import ejb.ClienteManager;
import modelos.Cliente;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by andrea on 27/02/16.
 */
@Path("clientes")
public class ClienteRest {

    @EJB
    ClienteManager clienteManager;

    /**
     * Lista todo los clientes
     * @return List<Cliente>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() {
        return clienteManager.listarClientes();
    }

    /**
     * Crea un cliente
     * @param cliente
     * @return
     */
    @POST
    @Consumes("application/json")
    public Cliente crearCliente(Cliente cliente) {
        return clienteManager.crearCliente(cliente);
    }

    /**
     * Retorna un cliente de acuerdo al id recibido como parametro
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cliente buscarCliente(@PathParam("id") Integer id) {
        return clienteManager.buscarClienteConId(id);
    }

    /**
     * Modifica un cliente
     * @param cliente
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente modificarCliente(Cliente cliente) {
        return clienteManager.modificarCliente(cliente);
    }
}
