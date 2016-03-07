package rest;

import modelos.Cliente;
import servicios.ClienteServicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 27/02/16.
 */
@Path("/clientes")
public class ClienteRest {

    @Inject
    private ClienteServicios clienteServicios;

    /**
     * Lista todos los clientes
     * @return List<Cliente>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() {
         return clienteServicios.getClientes();
    }

    /**
     * Crea un cliente
     * @param cliente
     * @return
     */
    @POST
    @Consumes("application/json")
    public Cliente crearCliente(Cliente cliente) {
        clienteServicios.agregarCliente(cliente);
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
        return clienteServicios.buscarCliente(id);
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
        return clienteServicios.modificarCliente(clienteModificado);
    }

    /**
     * Eliminar cliente
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response eliminarCliente(@PathParam("id") Integer id) {
        clienteServicios.eliminarCliente(id);
        return Response.status(200).build();
    }


}
